package when_how.hero.battle.data;

import when_how.hero.sdata.cache.SHeroCache;
import when_how.hero.sdata.domain.SHero;

public class Hero extends Entity {

	private HeroSkill skill;
	
	private Equip equip;

	public Hero(int heroId) {
		SHero sHero = SHeroCache.CACHE.get(heroId);
		HeroSkill heroSkill = new HeroSkill(sHero.getSkillId());
		this.setSid(sHero.getSid());
		this.setSkill(heroSkill);
		this.setHp(sHero.getHp());
		this.setHpMax(sHero.getHp());
	}

	public HeroSkill getSkill() {
		return skill;
	}

	public void setSkill(HeroSkill skill) {
		this.skill = skill;
	}

	public Equip getEquip() {
		return equip;
	}

	public void setEquip(Equip equip) {
		this.equip = equip;
	}

}
