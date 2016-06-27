package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table = "hero_skill")
public class SHeroSkill {

	@SdataColumn(type = "id")
	private int sid;

	@SdataColumn
	private String name;

	@SdataColumn
	private String intro;

	@SdataColumn
	private int cost;

	@SdataColumn
	private int effectId;

	public int getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public int getCost() {
		return cost;
	}

	public int getEffectId() {
		return effectId;
	}

}
