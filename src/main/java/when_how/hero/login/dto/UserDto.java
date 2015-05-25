package when_how.hero.login.dto;

/**
 * @author when_how
 * 
 */

public class UserDto {
	/** 用户id */
	private Integer id;
	
	/** 用户账号 */
	private String account;
	
	/** 运营商标志 */
	private String yunying;
	
	private String accessToken360;
	
	private String userId360;
	
	private String refresh_token_360;
	
	private long expires_in_360;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getYunying() {
		return yunying;
	}

	public void setYunying(String yunying) {
		this.yunying = yunying;
	}

	public String getRefresh_token_360() {
		return refresh_token_360;
	}

	public void setRefresh_token_360(String refresh_token_360) {
		this.refresh_token_360 = refresh_token_360;
	}

	public long getExpires_in_360() {
		return expires_in_360;
	}

	public void setExpires_in_360(long expires_in_360) {
		this.expires_in_360 = expires_in_360;
	}

	public String getAccessToken360() {
		return accessToken360;
	}

	public void setAccessToken360(String accessToken360) {
		this.accessToken360 = accessToken360;
	}

	public String getUserId360() {
		return userId360;
	}

	public void setUserId360(String userId360) {
		this.userId360 = userId360;
	}

}
