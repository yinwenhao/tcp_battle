package when_how.hero.request.service;

import when_how.hero.common.json.MyResponse;
import when_how.hero.request.dto.UserDto;

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
	 * @param userDto
	 * @return
	 */
	MyResponse login(String account, String password, UserDto userDto);

	/**
	 * 用户退出
	 * 
	 * @param playerId
	 * @return
	 */
	String logout(int playerId);

}
