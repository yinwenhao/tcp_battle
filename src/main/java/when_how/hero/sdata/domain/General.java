package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * @author when_how
 *
 */
@SdataTable(table="general")
public class General implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4534026468815849441L;

	@SdataColumn(type="id")
	private int id;
	
	/** 武将名称 */
	@SdataColumn
	private String name;
	
	/** 初始血量 */
	@SdataColumn
	private int hp;
	
	@SdataColumn
	private int bing_zhong; // 兵种id
	
	@SdataColumn
	private int att; // 攻击力
	
	@SdataColumn
	private int def; // 防御力
	
	@SdataColumn
	private int jc_att; // 计策攻击力
	
	@SdataColumn
	private int jc_def; // 计策防御力
	
	@SdataColumn
	private int ele; // 属性

	@SdataColumn
	private int hp_grow; // hp成长
	
	@SdataColumn
	private int att_grow; // 攻击力成长
	
	@SdataColumn
	private int def_grow; // 防御力成长
	
	@SdataColumn
	private int jc_att_grow; // 计策攻击力成长
	
	@SdataColumn
	private int jc_def_grow; // 计策防御力成长
	
	@SdataColumn
	private int skill; // 技能
	
	@SdataColumn
	private int att_skill; // 普通攻击技能
	
	@SdataColumn
	private int quality; // 品质
	
	@SdataColumn
	private int weight; // 出现权重
	
	@SdataColumn
	private int need_level; // 上阵需要的玩家等级
	
	/**
	 * 根据等级获得hp
	 * @param lv
	 * @param grow
	 * @return
	 */
	public int realGetHp(int lv, int grow) {
		return hp + (grow * lv);
	}
	
	/**
	 * 根据等级获得攻击值
	 * @param lv
	 * @param grow
	 * @return
	 */
	public int realGetAtt(int lv, int grow) {
		return att + (grow * lv);
	}
	
	/**
	 * 根据等级获得防御值
	 * @param lv
	 * @param grow
	 * @return
	 */
	public int realGetDef(int lv, int grow) {
		return def + (grow * lv);
	}
	
	/**
	 * 根据等级获得计策攻击值
	 * @param lv
	 * @param grow
	 * @return
	 */
	public int realGetJcAtt(int lv, int grow) {
		return jc_att + (grow * lv);
	}
	
	/**
	 * 根据等级获得计策防御值
	 * @param lv
	 * @param grow
	 * @return
	 */
	public int realGetJcDef(int lv, int grow) {
		return jc_def + (grow * lv);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getBing_zhong() {
		return bing_zhong;
	}

	public void setBing_zhong(int bing_zhong) {
		this.bing_zhong = bing_zhong;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getJc_att() {
		return jc_att;
	}

	public void setJc_att(int jc_att) {
		this.jc_att = jc_att;
	}

	public int getJc_def() {
		return jc_def;
	}

	public void setJc_def(int jc_def) {
		this.jc_def = jc_def;
	}

	public int getEle() {
		return ele;
	}

	public void setEle(int ele) {
		this.ele = ele;
	}

	public int getHp_grow() {
		return hp_grow;
	}

	public void setHp_grow(int hp_grow) {
		this.hp_grow = hp_grow;
	}

	public int getAtt_grow() {
		return att_grow;
	}

	public void setAtt_grow(int att_grow) {
		this.att_grow = att_grow;
	}

	public int getDef_grow() {
		return def_grow;
	}

	public void setDef_grow(int def_grow) {
		this.def_grow = def_grow;
	}

	public int getJc_att_grow() {
		return jc_att_grow;
	}

	public void setJc_att_grow(int jc_att_grow) {
		this.jc_att_grow = jc_att_grow;
	}

	public int getJc_def_grow() {
		return jc_def_grow;
	}

	public void setJc_def_grow(int jc_def_grow) {
		this.jc_def_grow = jc_def_grow;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getAtt_skill() {
		return att_skill;
	}

	public void setAtt_skill(int att_skill) {
		this.att_skill = att_skill;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getNeed_level() {
		return need_level;
	}

	public void setNeed_level(int need_level) {
		this.need_level = need_level;
	}
	
}
