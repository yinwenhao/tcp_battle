package when_how.hero.service;

import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface CardService {

	/**
	 * 出牌
	 * 
	 * @param uid
	 * @param targetPlayerIndex
	 *            目标玩家的index
	 * @param i
	 *            手牌的序号
	 * @param location
	 *            随从放置位置
	 * @param target
	 *            目标序号，-1表示英雄
	 * @param chooseOne
	 *            抉择
	 * @return
	 */
	MyResponse useCard(long uid, int targetPlayerIndex, int i, int location, int target, int chooseOne)
			throws MyException;

	/**
	 * 换牌
	 * 
	 * @param uid
	 * @param changeIndex
	 *            需要交换的手牌index列表
	 * @param turn
	 * @return
	 */
	MyResponse changeCardsInHand(long uid, int[] changeIndex, int turn) throws MyException;

	/**
	 * 选择一张牌，用于“发现”
	 * 
	 * @param uid
	 * @param i
	 *            选择的牌的序号
	 * @return
	 */
	MyResponse discoverOne(long uid, int i) throws MyException;

}
