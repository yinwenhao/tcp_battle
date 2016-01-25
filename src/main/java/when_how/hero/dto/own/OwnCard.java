package when_how.hero.dto.own;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Card;
import when_how.hero.sdata.domain.SEffect;

@JsonInclude(Include.NON_NULL)
public class OwnCard {
	
	private int sid;
	
	private int hp;

	private int att;

	private int cost;

	private int type;
	
	private int[] effects; // 受到的影响

	private int[] aureoleEffect; // 光环

	private int[] battlecryEffect; // 战吼

	private int[] deathrattleEffect; // 亡语

	private int[] inspireEffect; // 激励

	private int[] chooseoneEffect; // 抉择
	
	public OwnCard(Card card) {
		this.sid = card.getSid();
		this.hp = card.getHp();
		this.att = card.getAtt();
		this.cost = card.getCost();
		this.type = card.getType();
		this.effects = convertEffectListToIntArray(card.getEffects());
		this.aureoleEffect = convertEffectListToIntArray(card.getAureoleEffect());
		this.battlecryEffect = convertEffectListToIntArray(card.getBattlecryEffect());
		this.deathrattleEffect = convertEffectListToIntArray(card.getDeathrattleEffect());
		this.inspireEffect = convertEffectListToIntArray(card.getInspireEffect());
		this.chooseoneEffect = convertEffectListToIntArray(card.getChooseoneEffect());
	}
	
	private int[] convertEffectListToIntArray(List<SEffect> list) {
		int[] result = null;
		if (list != null) {
			result = new int[list.size()];
			for (int i=0; i<result.length; i++) {
				result[i] = list.get(i).getSid();
			}
		}
		return result;
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

	public int[] getEffects() {
		return effects;
	}

	public void setEffects(int[] effects) {
		this.effects = effects;
	}

	public int[] getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(int[] aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
	}

	public int[] getBattlecryEffect() {
		return battlecryEffect;
	}

	public void setBattlecryEffect(int[] battlecryEffect) {
		this.battlecryEffect = battlecryEffect;
	}

	public int[] getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(int[] deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

	public int[] getInspireEffect() {
		return inspireEffect;
	}

	public void setInspireEffect(int[] inspireEffect) {
		this.inspireEffect = inspireEffect;
	}

	public int[] getChooseoneEffect() {
		return chooseoneEffect;
	}

	public void setChooseoneEffect(int[] chooseoneEffect) {
		this.chooseoneEffect = chooseoneEffect;
	}

}
