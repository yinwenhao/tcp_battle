package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.HeroSkill;

@JsonInclude(Include.NON_NULL)
public class OwnHeroSkill {
	
	private int sid;
	
	private int cost;
	
	private boolean canUse;
	
	public OwnHeroSkill(HeroSkill heroSkill) {
		this.sid = heroSkill.getSid();
		this.cost = heroSkill.getCost();
		this.canUse = heroSkill.getCanUse();
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean getCanUse() {
		return canUse;
	}

	public void setCanUse(boolean canUse) {
		this.canUse = canUse;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

}
