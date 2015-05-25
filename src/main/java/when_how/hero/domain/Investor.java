package when_how.hero.domain;

import when_how.hero.hibernate.model.IModel;

/**
 * 投资人表
 * @author when_how
 * @hibernate.class table="investor" dynamic-insert="true" dynamic-update="true"
 * @hibernate.cache usage="read-write"
 */
public class Investor implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 533L;
	
	private int id;

	private String name;
	
	/** 简介 */
	private String info;
	
	/** 联系邮箱 */
	private String email;
	
	/** 联系电话 */
	private int phone;
	
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
	 * @hibernate.property column="name"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @hibernate.property column="info"
	 */
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @hibernate.property column="email"
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @hibernate.property column="phone"
	 */
	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}
