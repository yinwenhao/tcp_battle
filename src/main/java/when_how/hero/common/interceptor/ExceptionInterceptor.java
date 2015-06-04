package when_how.hero.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ExceptionInterceptor implements Interceptor {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5344811311496L;

	/** log */
	private final Logger log = LoggerFactory.getLogger(getClass());

	/** Log */
	private final Logger dayreportInterfaceLog = LoggerFactory.getLogger("dayreportInterface");

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		String actionName = "";
//		String methodName = "";
//		actionName = invocation.getAction().getClass().getSimpleName();
//		methodName = invocation.getProxy().getMethod();
//		long starttime = System.currentTimeMillis();
//		boolean exception = false;
//		try {
//			// 正常逻辑
//			return invocation.invoke();
//		} catch (AuthenticationException e) {
//			Object action = invocation.getAction();
//			if (action instanceof ValidationAware) {
//				ValidationAware aware = (ValidationAware) action;
//				aware.addActionMessage(e.getErrorCode());
//			}
//			exception = true;
//		} catch (Exception e) {
//			Object action = invocation.getAction();
//			if (action instanceof ValidationAware) {
//				ValidationAware aware = (ValidationAware) action;
//				aware.addActionMessage(LanguagePackage
//						.getProperties(PackageKeys.SYS_ERROR));
//
//				log.error("调用" + actionName + "的" + methodName + "方法，引发一个异常:");
//				if (e instanceof InvocationTargetException) {
//					log.error("E0010", ((InvocationTargetException) e)
//							.getTargetException());
//				} else {
//					log.error("E0010", e);
//				}
//			}
//			e.printStackTrace();
//			exception = true;
//		} catch (Throwable t) {
//			Object action = invocation.getAction();
//			if (action instanceof ValidationAware) {
//				ValidationAware aware = (ValidationAware) action;
//				aware.addActionMessage(LanguagePackage
//						.getProperties(PackageKeys.SYS_ERROR));
//
//				log.error("调用" + actionName + "的" + methodName + "方法，引发一个异常:");
//				if (t instanceof InvocationTargetException) {
//					log.error("E0010", ((InvocationTargetException) t)
//							.getTargetException());
//				} else {
//					log.error("E0010", t);
//				}
//			}
//			t.printStackTrace();
//			exception = true;
//		} finally {
//			if (!"BaseAction".equals(actionName)) {
//				PlayerDto playerDto = (PlayerDto) invocation
//						.getInvocationContext().getSession()
//						.get(MyConstants.PLAYER);
//				String ip = getIpAddr(ServletActionContext.getRequest());
//
//				// 记录接口调用日志
//				long endtime = System.currentTimeMillis();
//				if (playerDto != null) {
//					logger.debug(MyConstants.formatInterfaceLog(
//							playerDto.getPlayerId(), playerDto.getPlayerName(),
//							playerDto.getLevel(), ip, actionName, methodName,
//							invocation.getInvocationContext().getParameters(),
//							(endtime - starttime), playerDto.getUserId(),
//							exception));
//				} else {
//					logger.debug(MyConstants.formatInterfaceLog(0, null, 0, ip,
//							actionName, methodName, invocation
//									.getInvocationContext().getParameters(),
//							(endtime - starttime), 0, exception));
//				}
//			}
//		}
//		invocation.setResultCode(Action.ERROR);
		return Action.ERROR;
	}

	/**
	 * 获得真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("Cdn-Src-Ip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
