package when_how.hero.common.interceptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import when_how.hero.common.MyConstants;
import when_how.hero.common.exception.AuthenticationException;
import when_how.hero.common.one_login.PlayerLoginNanoTime;



import when_how.hero.request.dto.PlayerDto;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, Integer> map = new HashMap<String, Integer>();

	// 初始化Set
	static {
		// 需要验证方法名的，数字为：1
		map.put("LoginAction", 1);

		// 不需要验证的，数字为：0
		map.put("ResultAction", 0);
		map.put("PayAction", 0);
		map.put("SystemAction", 0);
	}

	private static Map<String, Set<String>> methodMap = new HashMap<String, Set<String>>();
	static {
		Set<String> loginSet = new HashSet<String>();
		loginSet.add("choosePlayer");
		loginSet.add("createNewPlayer");
		loginSet.add("execute");
		methodMap.put("LoginAction", loginSet);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String sessionId = request.getSession().getId();
//		String actionName = invocation.getAction().getClass().getSimpleName();
//		invocation.getInvocationContext().getSession()
//				.put("sessionId", sessionId);
//		if (map.containsKey(actionName)) {
//			if (map.get(actionName).equals(0)) {
//				// 不需要验证登录
//				return invocation.invoke();
//			} else if (map.get(actionName).equals(1)) {
//				// 需要验证方法名的
//				String methodName = invocation.getProxy().getMethod();
//				if (methodMap.get(actionName).contains(methodName)) {
//					// 这个方法不需要验证登录
//					return invocation.invoke();
//				}
//			}
//		}
//
//		// 需要验证是否登录
//		Map<String, Object> session = invocation.getInvocationContext()
//				.getSession();
//		if (!session.containsKey(MyConstants.PLAYER)) {
//			AuthenticationException e = new AuthenticationException();
//			e.setErrorCode(MyConstants.NOT_LOGIN);
//			// 没有登录，抛出异常
//			throw e;
//		} else if (PlayerLoginNanoTime.checkLogin((PlayerDto) session
//				.get(MyConstants.PLAYER)) < 0) {
//			AuthenticationException e = new AuthenticationException();
//			e.setErrorCode(MyConstants.LOGIN_IN_OTHER_PLACE);
//			session.remove(MyConstants.PLAYER);
//			session.remove(MyConstants.USER);
//			// 被挤下线，抛出异常
//			throw e;
//		}
//		// PlayerDto playerDto = (PlayerDto) session.get(MyConstants.PLAYER);
//		// if (PlayerLoginNanoTime.isFCMOn()
//		// && playerDto.getFcmMark() != MyConstants.FCM_MARK_2) {
//		// long onlineTimeNow = (System.nanoTime() - playerDto
//		// .getLoginNanoTime()) / 1000000 + playerDto.getOnlineTime();
//		// if (onlineTimeNow > PlayerLoginNanoTime.fcmHours2 * 60 * 60 * 1000) {
//		// PlayerLoginNanoTime.addFcmPlayer(playerDto.getPlayerId(),
//		// PlayerLoginNanoTime.fcmMark2);
//		// // 满5小时，每15分钟提示1次
//		// long minute = (onlineTimeNow - PlayerLoginNanoTime.fcmHours2 * 60 *
//		// 60 * 1000) / 1000 / 60;
//		// if (minute % 15 <= 1) {
//		// // 提示
//		// PlayerDataInterceptor.putMarkForLegion(
//		// playerDto.getPlayerId(), PlayerMarks.fcm_tips_2);
//		// }
//		// } else if (onlineTimeNow > PlayerLoginNanoTime.fcmHours * 60 * 60 *
//		// 1000) {
//		// PlayerLoginNanoTime.addFcmPlayer(playerDto.getPlayerId(),
//		// PlayerLoginNanoTime.fcmMark);
//		// // 满3小时，每30分钟提示1次
//		// long minute = (onlineTimeNow - PlayerLoginNanoTime.fcmHours * 60 * 60
//		// * 1000) / 1000 / 60;
//		// if (minute % 30 <= 1) {
//		// // 提示
//		// PlayerDataInterceptor.putMarkForLegion(
//		// playerDto.getPlayerId(), PlayerMarks.fcm_tips);
//		// }
//		// }
//		// }
		return invocation.invoke();
	}
}
