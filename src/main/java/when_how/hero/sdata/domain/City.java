package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * @author when_how
 *
 */
@SdataTable(table="city")
public class City implements IModel {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 202646115849441L;

	@SdataColumn(type="id")
	private int id;
	
	@SdataColumn
	private String name;
	
	@SdataColumn
	private int resource;
	
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

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

}
