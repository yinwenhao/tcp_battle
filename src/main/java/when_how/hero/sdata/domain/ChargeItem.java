package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="charge_item")
public class ChargeItem implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 20261849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private int base;
	
	@SdataColumn
	private int grow;
	
	@SdataColumn
	private String extra;
	
	@SdataColumn
	private int vip;
	
	@SdataColumn
	private String detail;
	
	@SdataColumn
	private int free_times;
	
	/**
	 * 获得递增金币
	 * @param count
	 * @return
	 */
	public int growPrice(int count) {
		if (count < free_times) {
			return 0;
		}
		return base + (count - free_times) * grow;
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

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getGrow() {
		return grow;
	}

	public void setGrow(int grow) {
		this.grow = grow;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getFree_times() {
		return free_times;
	}

	public void setFree_times(int free_times) {
		this.free_times = free_times;
	}

}
