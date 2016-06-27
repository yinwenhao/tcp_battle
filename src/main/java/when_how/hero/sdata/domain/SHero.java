package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table = "hero")
public class SHero {

	@SdataColumn(type = "id")
	private int sid;

	@SdataColumn
	private String name;

	@SdataColumn
	private String intro;

	@SdataColumn
	private int hp;

	@SdataColumn
	private int skillId;

	public int getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	public String getIntro() {
		return intro;
	}

	public int getHp() {
		return hp;
	}

	public int getSkillId() {
		return skillId;
	}

}
