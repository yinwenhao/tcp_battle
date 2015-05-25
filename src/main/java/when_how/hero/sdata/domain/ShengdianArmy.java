package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;
import when_how.hero.hibernate.model.IModel;

/**
 * 用户表
 * 
 * @author when_how
 * 
 */
@SdataTable(table = "shengdian_army")
public class ShengdianArmy implements IModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 202646885849441L;

	@SdataColumn(type = "id")
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
	private int level;

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
