package when_how.hero.battle.data;

import java.util.List;

import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.domain.SCard;
import when_how.hero.sdata.domain.SEffect;

public class Servant extends Entity {

	private boolean summoningSickness; // 是否召唤失调

	private List<SEffect> aureoleEffect; // 光环

	private List<SEffect> deathrattleEffect; // 亡语

	private List<SEffect> inspireEffect; // 激励

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

	@Override
	public boolean isCanAttack() {
		if (getAtt() <= 0) {
			return false;
		}
		if (getAttNum() > 0) {
			return false;
		}
		if (isSummoningSickness()) {
			return false;
		}
		return true;
	}

	public List<SEffect> getAureoleEffect() {
		return aureoleEffect;
	}

	public void setAureoleEffect(List<SEffect> aureoleEffect) {
		this.aureoleEffect = aureoleEffect;
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

	public boolean isSummoningSickness() {
		return summoningSickness;
	}

	public void setSummoningSickness(boolean summoningSickness) {
		this.summoningSickness = summoningSickness;
	}

}
