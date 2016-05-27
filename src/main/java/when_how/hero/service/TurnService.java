package when_how.hero.service;

import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface TurnService {

	/**
	 * 结束回合
	 * 
	 * @param uid
	 * @return
	 */
	MyResponse endTurn(long uid, int turn);

	/**
	 * 认输
	 * 
	 * @param uid
	 * @return
	 */
	MyResponse concede(long uid);

}
