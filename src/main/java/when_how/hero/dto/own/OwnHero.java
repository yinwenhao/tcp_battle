package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Hero;

@JsonInclude(Include.NON_NULL)
public class OwnHero extends OwnEntity {

	private OwnHeroSkill skill;

	public OwnHero(Hero hero) {
		super(hero);
		this.skill = new OwnHeroSkill(hero.getSkill());
	}

	public OwnHeroSkill getSkill() {
		return skill;
	}

	public void setSkill(OwnHeroSkill skill) {
		this.skill = skill;
	}

}
