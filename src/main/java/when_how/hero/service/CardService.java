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
	 * 换牌，用于游戏开始时，更换手牌
	 * 
	 * @param uid
	 * @param changeIndex
	 *            需要交换的手牌index列表
	 * @return
	 */
	MyResponse changeCardsInHand(long uid, int[] changeIndex) throws MyException;

	/**
	 * "发现"一张牌
	 * 
	 * @param uid
	 * @param chooseIndex
	 *            选择的牌的序号
	 * @return
	 */
	MyResponse discoverOne(long uid, int chooseIndex) throws MyException;

	/**
	 * "收藏"一张牌
	 * 
	 * @param uid
	 * @param chooseIndex
	 *            选择的牌的序号
	 * @return
	 * @throws MyException
	 */
	MyResponse collectOne(long uid, int chooseIndex) throws MyException;

}
