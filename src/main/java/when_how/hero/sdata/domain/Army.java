package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="army")
public class Army implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2026468815849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private int hero;
	
	@SdataColumn
	private int general_1;
	
	@SdataColumn
	private int general_2;
	
	@SdataColumn
	private int general_3;
	
	@SdataColumn
	private int type;

	@SdataColumn
	private int pre;

	@SdataColumn
	private int level;
	
	@SdataColumn
	private int reward_iron;
	
	@SdataColumn
	private int reward_general;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHero() {
		return hero;
	}

	public void setHero(int hero) {
		this.hero = hero;
	}

	public int getGeneral_1() {
		return general_1;
	}

	public void setGeneral_1(int general_1) {
		this.general_1 = general_1;
	}

	public int getGeneral_2() {
		return general_2;
	}

	public void setGeneral_2(int general_2) {
		this.general_2 = general_2;
	}

	public int getGeneral_3() {
		return general_3;
	}

	public void setGeneral_3(int general_3) {
		this.general_3 = general_3;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getReward_iron() {
		return reward_iron;
	}

	public void setReward_iron(int reward_iron) {
		this.reward_iron = reward_iron;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReward_general() {
		return reward_general;
	}

	public void setReward_general(int reward_general) {
		this.reward_general = reward_general;
	}

}
