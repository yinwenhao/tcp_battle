package when_how.hero.login.dto;

/**
 * @author when_how
 * 
 */

public class PlayerDto {
	/** 角色ID */
	private int playerId;

	private int userId;

	/** 用户昵称 */
	private String playerName;

	/** 用户等级，登录时的 */
	private int level;

	/** 用户vip等级，登录时的 */
	private int vip;

	private int legion;
	
	// 联盟名称
	private String legionName;

	/** 本次登录的时间(纳秒) */
	private long loginNanoTime;

	/** 数据库记录的累计在线时间（毫秒） */
	private long onlineTime;

	/** 防沉迷标志，0未验证，1需要防沉迷，2不防沉迷 */
	private int fcmMark;

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getVip() {
		return vip;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLegion() {
		return legion;
	}

	public void setLegion(int legion) {
		this.legion = legion;
	}

	public long getLoginNanoTime() {
		return loginNanoTime;
	}

	public void setLoginNanoTime(long loginNanoTime) {
		this.loginNanoTime = loginNanoTime;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public int getFcmMark() {
		return fcmMark;
	}

	public void setFcmMark(int fcmMark) {
		this.fcmMark = fcmMark;
	}

	public String getLegionName() {
		return legionName;
	}

	public void setLegionName(String legionName) {
		this.legionName = legionName;
	}

}
