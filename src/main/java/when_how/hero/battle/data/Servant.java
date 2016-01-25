package when_how.hero.battle.data;

import java.util.List;

import when_how.hero.sdata.domain.SEffect;

public class Servant  extends Entity {
	
	private List<SEffect> effects; // 受到的影响
	
	private List<SEffect> aureoleEffect; // 光环

	private List<SEffect> battlecryEffect; // 战吼

	private List<SEffect> deathrattleEffect; // 亡语

	private List<SEffect> inspireEffect; // 激励

	private List<SEffect> chooseoneEffect; // 抉择
	
//	private boolean taunt; // 嘲讽
//	private boolean stealth; // 潜行
//	private boolean charge; // 冲锋
//	private boolean shengdun; // 圣盾
//	private boolean fenglu; // 风怒
	
	public Servant(Card card) {
		setSid(card.getSid());
		setAtt(card.getAtt());
		setHp(card.getHp());
		setHpMax(card.getHp());
		setEffects(card.getEffects());
		setAureoleEffect(card.getAureoleEffect());
		setBattlecryEffect(card.getBattlecryEffect());
		setDeathrattleEffect(card.getDeathrattleEffect());
		setInspireEffect(card.getInspireEffect());
		setChooseoneEffect(card.getChooseoneEffect());
	}

	public List<SEffect> getEffects() {
		return effects;
	}

	public void setEffects(List<SEffect> effects) {
		this.effects = effects;
	}

	public List<SEffect> getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(List<SEffect> aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
	}

	public List<SEffect> getBattlecryEffect() {
		return battlecryEffect;
	}

	public void setBattlecryEffect(List<SEffect> battlecryEffect) {
		this.battlecryEffect = battlecryEffect;
	}

	public List<SEffect> getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(List<SEffect> deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

	public List<SEffect> getInspireEffect() {
		return inspireEffect;
	}

	public void setInspireEffect(List<SEffect> inspireEffect) {
		this.inspireEffect = inspireEffect;
	}

	public List<SEffect> getChooseoneEffect() {
		return chooseoneEffect;
	}

	public void setChooseoneEffect(List<SEffect> chooseoneEffect) {
		this.chooseoneEffect = chooseoneEffect;
	}

}
