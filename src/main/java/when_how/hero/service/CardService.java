package when_how.hero.service;

import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface CardService {

	/**
	 * 出牌
	 * @param uid
	 * @param targetPlayerIndex 目标玩家的index
	 * @param i 手牌的序号
	 * @param location 随从放置位置
	 * @param target 目标序号，-1表示英雄
	 * @param chooseOne 抉择
	 * @return
	 */
	MyResponse useCard(long uid, int targetPlayerIndex, int i, int location, int target, int chooseOne);
	
	MyResponse changeCardsInHand(long uid, int[] changeIndex, int turn);

}
