package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Hero;

@JsonInclude(Include.NON_NULL)
public class OwnHero extends OwnEntity {

	private OwnHeroSkill skill;

	private OwnEquip equip;

	public OwnHero(Hero hero) {
		super(hero);
		this.skill = new OwnHeroSkill(hero.getSkill());
		if (hero.getEquip() != null) {
			this.equip = new OwnEquip(hero.getEquip());
		}
	}

	public OwnHeroSkill getSkill() {
		return skill;
	}

	public void setSkill(OwnHeroSkill skill) {
		this.skill = skill;
	}

	public OwnEquip getEquip() {
		return equip;
	}

	public void setEquip(OwnEquip equip) {
		this.equip = equip;
	}

}
