package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * @author when_how
 *
 */
@SdataTable(table="power_army")
public class PowerArmy implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2026465849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private String armies;
	
	@SdataColumn
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArmies() {
		return armies;
	}

	public void setArmies(String armies) {
		this.armies = armies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
