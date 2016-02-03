package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.HeroSkill;

@JsonInclude(Include.NON_NULL)
public class OwnHeroSkill {

	private int sid;

	private int cost;

	private int useNum;

	public OwnHeroSkill(HeroSkill heroSkill) {
		this.sid = heroSkill.getSid();
		this.cost = heroSkill.getCost();
		this.setUseNum(heroSkill.getUseNum());
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
