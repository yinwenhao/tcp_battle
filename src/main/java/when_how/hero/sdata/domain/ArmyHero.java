package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="army_hero")
public class ArmyHero implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2026468815849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private int att;
	
	@SdataColumn
	private int jc_att;
	
	@SdataColumn
	private int bing_zhong;
	
	@SdataColumn
	private String skill_list;
	
	@SdataColumn
	private int att_skill;

	@SdataColumn
	private String name;
	
	@SdataColumn
	private int lv;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getJc_att() {
		return jc_att;
	}

	public void setJc_att(int jc_att) {
		this.jc_att = jc_att;
	}

	public int getBing_zhong() {
		return bing_zhong;
	}

	public void setBing_zhong(int bing_zhong) {
		this.bing_zhong = bing_zhong;
	}

	public String getSkill_list() {
		return skill_list;
	}

	public void setSkill_list(String skill_list) {
		this.skill_list = skill_list;
	}

	public int getAtt_skill() {
		return att_skill;
	}

	public void setAtt_skill(int att_skill) {
		this.att_skill = att_skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}
	
}
