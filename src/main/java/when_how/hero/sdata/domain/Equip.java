package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="equip")
public class Equip implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2026368815849441L;

	@SdataColumn(type="id")
	private int id;
	
	// 装备类型
	@SdataColumn
	private int equip_type;
	
	@SdataColumn
	private int quality;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private int hp_grow;
	
	@SdataColumn
	private int att_grow;
	
	@SdataColumn
	private int def_grow;
	
	@SdataColumn
	private int jc_att_grow;
	
	@SdataColumn
	private int jc_def_grow;
	
	// 可以装备的等级
	@SdataColumn
	private int can_equip_level;
	
	// 可以制作的等级
	@SdataColumn
	private int level;
	
	@SdataColumn
	private int cost;
	
	@SdataColumn
	private int up_cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEquip_type() {
		return equip_type;
	}

	public void setEquip_type(int equip_type) {
		this.equip_type = equip_type;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getUp_cost() {
		return up_cost;
	}

	public void setUp_cost(int up_cost) {
		this.up_cost = up_cost;
	}

	public int getCan_equip_level() {
		return can_equip_level;
	}

	public void setCan_equip_level(int can_equip_level) {
		this.can_equip_level = can_equip_level;
	}

}
