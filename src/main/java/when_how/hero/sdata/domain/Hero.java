package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="hero")
public class Hero implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 202646349441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private int att_grow;
	
	@SdataColumn
	private int jc_att_grow;
	
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
	private int quality;
	
	/**
	 * 根据等级获得攻击值
	 * @param lv
	 * @return
	 */
	public int realGetAtt(int lv) {
		return att + (att_grow * lv);
	}
	
	/**
	 * 根据等级获得计策攻击值
	 * @param lv
	 * @return
	 */
	public int realGetJcAtt(int lv) {
		return jc_att + (jc_att_grow * lv);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAtt_grow() {
		return att_grow;
	}

	public void setAtt_grow(int att_grow) {
		this.att_grow = att_grow;
	}

	public int getJc_att_grow() {
		return jc_att_grow;
	}

	public void setJc_att_grow(int jc_att_grow) {
		this.jc_att_grow = jc_att_grow;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtt_skill() {
		return att_skill;
	}

	public void setAtt_skill(int att_skill) {
		this.att_skill = att_skill;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
}
