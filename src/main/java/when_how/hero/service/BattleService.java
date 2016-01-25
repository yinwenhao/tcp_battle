package when_how.hero.service;

import when_how.hero.common.json.MyResponse;

/**
 * @author when_how
 * 
 */

public interface BattleService {

	/**
	 * 出牌
	 * @param uid
	 * @param i 手牌的序号
	 * @param location 随从放置位置
	 * @param target 卡牌效果目标
	 * @param chooseOne 抉择
	 * @return
	 */
	MyResponse useCard(long uid, int i, int location, int target, int chooseOne);

}
