package when_how.hero.netty.dispatcher;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import when_how.hero.common.MyConstants;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.common.one_login.PlayerLoginTime;
import when_how.hero.netty.MySession;
import when_how.hero.netty.MyTcpConstants;
import when_how.hero.request.dto.UserDto;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyDispatcher {
	
	private final static Map<Short, String[]> ACTION_MAP_NOT_CHECK_LOGIN = new HashMap<Short, String[]>();
	private final static Map<Short, String[]> ACTION_MAP = new HashMap<Short, String[]>();

	static {
		// 这里添加不需要验证登陆的接口，从100开始到999
		addActionNotCheckLogin((short) 100, "loginAction", "login");
		
		// 这里添加需要验证登陆的接口，从1000开始，到Short.MAX_VALUE = 32767
		addAction((short) 1000, "loginAction", "logout");
	}
	
	public static void addAction(short jiekouId, String actionSpringBeanName, String methodName) {
		doAddAction(jiekouId, actionSpringBeanName, methodName, true);
	}
	
	public static void addActionNotCheckLogin(short jiekouId, String actionSpringBeanName, String methodName) {
		doAddAction(jiekouId, actionSpringBeanName, methodName, false);
	}
	
	/**
	 * 增加用户接口id
	 * @param jiekouId
	 * @param actionSpringBeanName
	 * @param methodName
	 * @param checkLogin
	 */
	private static void doAddAction(short jiekouId, String actionSpringBeanName, String methodName, boolean checkLogin) {
		String[] ss = new String[] {actionSpringBeanName, methodName};
		if (checkLogin) {
			ACTION_MAP.put(jiekouId, ss);
		} else {
			ACTION_MAP_NOT_CHECK_LOGIN.put(jiekouId, ss);
		}
	}

	public static MyResponse getResult(short jiekouId, String param, MySession session) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> paramMap = objectMapper.readValue(param, Map.class);
		String[] actionClassNameAndMethod;
		if (ACTION_MAP_NOT_CHECK_LOGIN.containsKey(jiekouId)) {
			actionClassNameAndMethod = ACTION_MAP_NOT_CHECK_LOGIN.get(jiekouId);
		} else if (ACTION_MAP.containsKey(jiekouId)) {
			if (session.containsKey(MyConstants.SESSION_KEY_USER)) {
				UserDto userDto = (UserDto) session.get(MyConstants.SESSION_KEY_USER);
				if (PlayerLoginTime.checkLogin(userDto) < 0) {
					MyResponse responseJSON = new MyResponse(MyErrorMessage.needLogin);
					return responseJSON;
				}
			}
			actionClassNameAndMethod = ACTION_MAP.get(jiekouId);
		} else {
			// 接口号错误
			MyResponse responseJSON = new MyResponse(MyErrorMessage.wrongJiekouId);
			return responseJSON;
		}
		return getResult(actionClassNameAndMethod[0], actionClassNameAndMethod[1], paramMap, session);
	}
	
	public static MyResponse getResult(String actionClassBean, String method, Map<String, Object> param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return getResult(actionClassBean, method, param, null);
	}
	
	private static MyResponse getResult(String actionClassBean, String method, Map<String, Object> param, MySession session) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object action = MyTcpConstants.factory.getBean(actionClassBean);
		Class<? extends Object> clazz = action.getClass();

		// 设置session
		clazz.getMethod("setSession", MySession.class).invoke(action, session);
		// 把参数赋值
		for (String key : param.keySet()) {
			Field f = null;
			try {
				f = clazz.getDeclaredField(key);
			} catch (NoSuchFieldException e) {
				continue;
			}
			String setter = "set" + f.getName().substring(0, 1).toUpperCase()
					+ f.getName().substring(1);
			Method m = clazz.getMethod(setter, f.getType());
			Object value = null;
			if (f.getType().isAssignableFrom(int.class)
					|| f.getType().isAssignableFrom(Integer.class)) {
				value = (Integer) param.get(key);
			} else if (f.getType().isAssignableFrom(float.class)
					|| f.getType().isAssignableFrom(Float.class)) {
				value = (Short) param.get(key);
			} else if (f.getType().isAssignableFrom(long.class)
					|| f.getType().isAssignableFrom(Long.class)) {
				value = (Long) param.get(key);
			} else if (f.getType().isAssignableFrom(double.class)
					|| f.getType().isAssignableFrom(Double.class)) {
				value = (Double) param.get(key);
			} else if (f.getType().isAssignableFrom(String.class)) {
				value = (String) param.get(key);
			}
			if (value != null) {
				m.invoke(action, value);
			}
		}

		// 执行action
		Method m = clazz.getMethod(method);
		m.invoke(action);

		// 设置返回值
		Method m2 = clazz.getMethod("getResponse");
		MyResponse responseJSON = (MyResponse) m2.invoke(action);
		return responseJSON;
	}
}
