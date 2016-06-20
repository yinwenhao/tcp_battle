package when_how.hero.service;

import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface LoginService {

	/**
	 * 连接战斗服务器，开始对战
	 * 
	 * @param token
	 * @return
	 * @throws MyException
	 */
	MyResponse login(String token) throws MyException;

}
