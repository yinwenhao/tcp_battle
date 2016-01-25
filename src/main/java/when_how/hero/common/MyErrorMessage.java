package when_how.hero.common;

/**
 * 100一下为系统预留，请定义100以上的接口号
 * @author when_how
 *
 */
public class MyErrorMessage {
	
	/** 成功 */
	public static final int success = 0;
	
	/** 未登陆，或被挤下线*/
	public static final int needLogin = 1;
	/** 错误的接口名 */
	public static final int wrongAction = 2;
	/** 不是你的回合 */
	public static final int notYourTurn = 3;
	/** 参数错误 */
	public static final int wrongParam = 4;

}
