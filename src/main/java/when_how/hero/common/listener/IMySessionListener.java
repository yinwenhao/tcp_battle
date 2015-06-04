package when_how.hero.common.listener;

import when_how.hero.netty.MySession;


public interface IMySessionListener {
	
	/**
	 * mySession创建时调用，mySession已经创建
	 */
	public void sessionCreated(MySession mySession);

	/**
	 * mySession销毁时调用，mySession还没有销毁
	 */
	public void sessionDestroyed(MySession mySession);

}
