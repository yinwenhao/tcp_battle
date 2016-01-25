package when_how.hero.sdata.domain;

import when_how.hero.common.annotation.SdataColumn;
import when_how.hero.common.annotation.SdataTable;

/**
 * 
 * @author when_how
 *
 */
@SdataTable(table="hero")
public class SHero {

	@SdataColumn(type="id")
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

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
}
