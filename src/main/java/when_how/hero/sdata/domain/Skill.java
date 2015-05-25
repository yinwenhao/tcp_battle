package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="skill")
public class Skill implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2026468815849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private int power;
	
	@SdataColumn
	private int effect;
	
	@SdataColumn
	private int mz;
	
	@SdataColumn
	private int type;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private String info;
	
	@SdataColumn
	private int ele;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public int getMz() {
		return mz;
	}

	public void setMz(int mz) {
		this.mz = mz;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getEle() {
		return ele;
	}

	public void setEle(int ele) {
		this.ele = ele;
	}

}
