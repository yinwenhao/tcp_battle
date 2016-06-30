package when_how.hero.dto.own;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Card;

@JsonInclude(Include.NON_NULL)
public class OwnCard {

	private int sid;

	private int hp;

	private int att;

	private int cost;

	private int type;

	private List<Integer> effects; // 受到的影响

	private List<Integer> aureoleEffect; // 光环

	private List<Integer> battlecryEffect; // 战吼

	private List<Integer> deathrattleEffect; // 亡语

	private List<Integer> inspireEffect; // 激励

	private int[] chooseone; // 抉择

	public OwnCard(Card card) {
		this.sid = card.getSid();
		this.hp = card.getHp();
		this.att = card.getAtt();
		this.cost = card.getCost();
		this.type = card.getType();
		this.effects = card.getEffects();
		this.aureoleEffect = card.getAureoleEffect();
		this.battlecryEffect = card.getBattlecryEffect();
		this.deathrattleEffect = card.getDeathrattleEffect();
		this.inspireEffect = card.getInspireEffect();
		this.chooseone = card.getChooseone();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Integer> getEffects() {
		return effects;
	}

	public void setEffects(List<Integer> effects) {
		this.effects = effects;
	}

	public List<Integer> getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(List<Integer> aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
	}

	public List<Integer> getBattlecryEffect() {
		return battlecryEffect;
	}

	public void setBattlecryEffect(List<Integer> battlecryEffect) {
		this.battlecryEffect = battlecryEffect;
	}

	public List<Integer> getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(List<Integer> deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

	public List<Integer> getInspireEffect() {
		return inspireEffect;
	}

	public void setInspireEffect(List<Integer> inspireEffect) {
		this.inspireEffect = inspireEffect;
	}

	public int[] getChooseone() {
		return chooseone;
	}

	public void setChooseone(int[] chooseone) {
		this.chooseone = chooseone;
	}

}
