package when_how.hero.battle.data;

import when_how.hero.sdata.cache.SHeroSkillCache;
import when_how.hero.sdata.domain.SHeroSkill;

public class HeroSkill {
	
	private int sid;
	
	private int cost;
	
	private int useNum; // 本回合中，已经使用的次数
	
	public HeroSkill(int skillId) {
		SHeroSkill sHeroSkill = SHeroSkillCache.CACHE.get(skillId);
		this.setSid(sHeroSkill.getSid());
		this.setUseNum(0);
		this.setCost(sHeroSkill.getCost());
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getUseNum() {
		return useNum;
	}

	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}

}
