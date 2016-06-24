package when_how.hero.constants;

/**
 * @author when_how
 *
 */
public class MyErrorNo {

	/** 成功 */
	public static final int success = 0;
	/** 通用错误——网络或系统异常，请稍后重试 */
	public static final int commonError = 1;

	/** 未登陆，或被挤下线 */
	public static final int needLogin = 10;
	/** 错误的接口名 */
	public static final int wrongAction = 20;
	/** 不是你的回合 */
	public static final int notYourTurn = 30;
	/** 参数错误 */
	public static final int wrongParam = 40;
	/** 不能攻击 */
	public static final int cannotAttack = 50;
	/** 能量不足 */
	public static final int notEnoughEnergy = 60;
	/** 不能更改 */
	public static final int cannotChange = 70;
	/** 请求过期 */
	public static final int requestOutOfDate = 80;
	/** 随从数量达到上限 */
	public static final int servantNumberLimit = 90;
	/** 错误的战斗 */
	public static final int wrongBattle = 100;
	/** json相关错误 */
	public static final int jsonError = 110;
	/** 没有战斗 */
	public static final int noBattle = 120;
	/** 没有“发现” */
	public static final int noDiscover = 130;
	/** 没有“收藏” */
	public static final int noCollect = 140;

}
