package when_how.hero.service;

import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface BattleService {

	/**
	 * 使用英雄技能
	 * 
	 * @param uid
	 * @param targetPlayerIndex
	 *            目标玩家的index
	 * @param target
	 * @return
	 */
	MyResponse useHeroSkill(long uid, int targetPlayerIndex, int target) throws MyException;

	/**
	 * 英雄攻击
	 * 
	 * @param uid
	 * @param targetPlayerIndex
	 *            目标玩家的index
	 * @param target
	 *            目标序号，-1表示英雄
	 * @return
	 */
	MyResponse heroAttack(long uid, int targetPlayerIndex, int target) throws MyException;

	/**
	 * 随从攻击
	 * 
	 * @param uid
	 * @param targetPlayerIndex
	 *            目标玩家的index
	 * @param servantIndex
	 *            随从的序号
	 * @param target
	 *            目标序号，-1表示英雄
	 * @return
	 */
	MyResponse servantAttack(long uid, int targetPlayerIndex, int servantIndex, int target) throws MyException;

}
