package when_how.hero.netty.dispatcher;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import when_how.hero.common.json.MyResponse;
import when_how.hero.netty.MyTcpConstants;

public class MyDispatcher {

	public static MyResponse getResult(String actionClassBean, String method, Map<String, Object> param) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object action = MyTcpConstants.factory.getBean(actionClassBean);
		Class<? extends Object> clazz = action.getClass();

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
