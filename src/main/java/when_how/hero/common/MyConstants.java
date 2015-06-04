package when_how.hero.common;

public class MyConstants {
	
	public static final int PAGE_MAX = 50;
	public static final int ROWS_PER_PAGE = 30;

	public static final String SESSION_KEY_PLAYER = "player";

	private static final String LOG_SPLIT = "#";

	/**
	 * 接口调用日志
	 * 
	 * @param playerId
	 * @param playerName
	 * @param playerLevel
	 * @param ip
	 * @param jiekouId
	 * @param useTime
	 * @param userId
	 * @param content
	 * @return
	 */
	public static String formatInterfaceLog(int playerId, String playerName, int playerLevel, String ip, int jiekouId, long useTime, int userId, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append(playerId).append(LOG_SPLIT).append(playerName).append(LOG_SPLIT).append(playerLevel).append(LOG_SPLIT);
		sb.append(ip).append(LOG_SPLIT).append(jiekouId).append(LOG_SPLIT).append(useTime).append(LOG_SPLIT).append(userId).append(LOG_SPLIT).append(content);
		return sb.toString();
	}

}
