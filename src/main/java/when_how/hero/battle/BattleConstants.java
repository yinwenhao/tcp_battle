package when_how.hero.battle;

public class BattleConstants {

	/**
	 * 随从位置——默认最右边
	 */
	public static final int LOCATION_DEFAULT = -1;

	/**
	 * 选择类型——发现
	 */
	public static final int CHOOSE_TYPE_DISCOVER = 1;

	/**
	 * 选择类型——收藏
	 */
	public static final int CHOOSE_TYPE_COLLECT = 2;

	/**
	 * 指定目标——英雄
	 */
	public static final int TARGET_HERO = -1;

	/**
	 * 战斗结果——未定
	 */
	public static final int BATTLE_RESULT_DEFAULT = 0;

	/**
	 * 战斗结果——胜利
	 */
	public static final int BATTLE_RESULT_WIN = 1;

	/**
	 * 战斗结果——失败
	 */
	public static final int BATTLE_RESULT_LOSE = 2;

	/**
	 * 随从最大数量
	 */
	public static final int SERVANTS_NUM_MAX = 7;

	/**
	 * 每回合增加的能量
	 */
	public static final int ENERGY_PER_TURN = 1;

	/**
	 * 能量上限
	 */
	public static final int ENERGY_LIMIT = 10;

	/**
	 * 手牌数上限
	 */
	public static final int HAND_LIMIT = 10;

	/**
	 * 初始能量大小
	 */
	public static final int ENERGY_INIT = 0;

	/**
	 * 先手初始化手牌数
	 */
	public static final int FIRST_SIDE_HAND_LIMIT = 3;

	/**
	 * 后手初始化手牌数
	 */
	public static final int SECOND_SIDE_HAND_LIMIT = 4;

	/**
	 * 卡牌类型——随从
	 */
	public static final int CARD_TYPE_SERVANT = 0;

	/**
	 * 卡牌类型——法术
	 */
	public static final int CARD_TYPE_SPELL = 1;

	/**
	 * 卡牌类型——装备
	 */
	public static final int CARD_TYPE_EQUIP = 2;

}
