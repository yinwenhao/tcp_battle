package when_how.hero.request.dto;

import when_how.hero.hibernate.dto.IDto;

/**
 * @author when_how
 * 
 */

public class UserDto implements IDto {
	/** 用户id */
	private Integer id;

	/** 昵称 */
	private String name;
	
	/** 用户等级 */
	private int user_lv;
	
	/** 用户vip等级 */
	private int vip;
	
	/** 登陆时间 */
	private long loginTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUser_lv() {
		return user_lv;
	}

	public void setUser_lv(int user_lv) {
		this.user_lv = user_lv;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

}
