package when_how.hero.battle.data;

import java.util.List;

import when_how.hero.battle.listener.SummoningSicknessListener;
import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.domain.SCard;
import when_how.hero.sdata.domain.SEffect;

public class Servant extends Entity {

	private boolean summoningSickness; // 是否召唤失调

	private List<Integer> aureoleEffect; // 光环

	private List<Integer> deathrattleEffect; // 亡语

	private List<Integer> inspireEffect; // 激励

	private SummoningSicknessListener summoningSicknessListener;

	public Servant(Card card) {
		setSid(card.getSid());
		setAtt(card.getAtt());
		setHp(card.getHp());
		setHpMax(card.getHp());
		setEffect(card.getEffects());

		setAureoleEffect(card.getAureoleEffect());
		setDeathrattleEffect(card.getDeathrattleEffect());
		setInspireEffect(card.getInspireEffect());

		// 召唤失调
		setSummoningSickness(true);
	}

	/**
	 * 是否可以攻击
	 * 
	 * @return
	 */
	@Override
	public boolean isCanAttack() {
		return !isSummoningSickness() && super.isCanAttack();
	}

	/**
	 * 被沉默
	 */
	public void beSilence() {
		SCard s = SCardCache.CACHE.get(getSid());
		setEffect(null);
		setAureoleEffect(null);
		setDeathrattleEffect(null);
		setInspireEffect(null);
		setAtt(s.getAtt());
		setHpMax(s.getHp());
		if (getHp() > getHpMax()) {
			setHp(getHpMax());
		}
	}

	public List<Integer> getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(List<Integer> aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
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

	public boolean isSummoningSickness() {
		if (summoningSicknessListener != null) {
			return summoningSicknessListener.isSummoningSickness(summoningSickness);
		}
		return summoningSickness;
	}

	public void setSummoningSickness(boolean summoningSickness) {
		this.summoningSickness = summoningSickness;
	}

	public void setSummoningSicknessListener(SummoningSicknessListener summoningSicknessListener, SEffect effect) {
		this.summoningSicknessListener = summoningSicknessListener;
		addEffect(effect);
	}

}
