package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * @author when_how
 *
 */
@SdataTable(table="vip")
public class Vip implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3468815849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private int gold;
	
	@SdataColumn
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
