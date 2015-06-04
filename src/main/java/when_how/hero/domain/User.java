package when_how.hero.domain;

import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 * @hibernate.class table="my_user" dynamic-insert="true" dynamic-update="true"
 * @hibernate.cache usage="read-write"
 */
public class User implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	/** 用户id */
	private int id;

	/** 昵称 */
	private String name;
	
	/** 用户账号 */
	private String account;
	
	/** 用户密码 */
	private String password;
	
	/** 用户等级 */
	private int user_lv;
	
	/** 用户vip等级 */
	private int vip;
	
	/** 开发者id */
	private int developer_id;

	/** 投资者id */
	private int Investor_id;
	
	/**
	 * @hibernate.id unsaved-value="null"
	 * @hibernate.column name="id"
	 * @hibernate.generator class="native"
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="account"
	 */
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @hibernate.property column="password"
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @hibernate.property column="name"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="user_lv"
	 */
	public int getUser_lv() {
		return user_lv;
	}

	public void setUser_lv(int user_lv) {
		this.user_lv = user_lv;
	}

	/**
	 * @hibernate.property column="vip"
	 */
	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	/**
	 * @hibernate.property column="developer_id"
	 */
	public int getDeveloper_id() {
		return developer_id;
	}

	public void setDeveloper_id(int developer_id) {
		this.developer_id = developer_id;
	}

	/**
	 * @hibernate.property column="Investor_id"
	 */
	public int getInvestor_id() {
		return Investor_id;
	}

	public void setInvestor_id(int investor_id) {
		Investor_id = investor_id;
	}

}
