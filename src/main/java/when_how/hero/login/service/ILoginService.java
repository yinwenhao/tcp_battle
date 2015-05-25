package when_how.hero.login.service;

import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface ILoginService {

	/**
	 * 用户登陆
	 * 
	 * @param account
	 *            用户账号
	 * @param password
	 *            用户密码
	 * @return
	 */
	MyResponse login(String account, String password);

	/**
	 * 用户退出
	 * 
	 * @param playerId
	 * @return
	 */
	String logout(int playerId);
	
}
