package when_how.hero.dto.own;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Servant;
import when_how.hero.sdata.domain.SEffect;

@JsonInclude(Include.NON_NULL)
public class OwnServant extends OwnEntity {

	private boolean summoningSickness; // 是否召唤失调

	private int[] effects; // 受到的影响

	private int[] aureoleEffect; // 光环

	private int[] battlecryEffect; // 战吼

	private int[] deathrattleEffect; // 亡语

	private int[] inspireEffect; // 激励

	private int[] chooseoneEffect; // 抉择

	// private boolean taunt; // 嘲讽
	// private boolean stealth; // 潜行
	// private boolean charge; // 冲锋
	// private boolean shengdun; // 圣盾
	// private boolean fenglu; // 风怒

	public OwnServant(Servant servant) {
		super(servant);
		this.summoningSickness = servant.isSummoningSickness();
		this.effects = convertEffectListToIntArray(servant.getEffects());
		this.aureoleEffect = convertEffectListToIntArray(servant
				.getAureoleEffect());
		this.battlecryEffect = convertEffectListToIntArray(servant
				.getBattlecryEffect());
		this.deathrattleEffect = convertEffectListToIntArray(servant
				.getDeathrattleEffect());
		this.inspireEffect = convertEffectListToIntArray(servant
				.getInspireEffect());
		this.chooseoneEffect = convertEffectListToIntArray(servant
				.getChooseoneEffect());
	}

	private int[] convertEffectListToIntArray(List<SEffect> list) {
		int[] result = null;
		if (list != null) {
			result = new int[list.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = list.get(i).getSid();
			}
		}
		return result;
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

	public boolean isSummoningSickness() {
		return summoningSickness;
	}

	public void setSummoningSickness(boolean summoningSickness) {
		this.summoningSickness = summoningSickness;
	}

}
