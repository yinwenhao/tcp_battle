package when_how.hero.battle.data;

import java.util.ArrayList;
import java.util.List;

import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.cache.SEffectCache;
import when_how.hero.sdata.domain.SCard;
import when_how.hero.sdata.domain.SEffect;

public class Card {

	// 疲劳时的扣血量
	private int damage = 0;

	private int sid;

	private int hp;

	private int att;

	private int cost;

	private int type;

	private List<SEffect> effects; // 受到的影响

	private List<SEffect> spellEffect; // 法术效果

	private List<SEffect> aureoleEffect; // 光环

	private List<SEffect> battlecryEffect; // 战吼

	private List<SEffect> deathrattleEffect; // 亡语

	private List<SEffect> inspireEffect; // 激励

	private int[] chooseone; // 抉择

	public Card() {

	}

	public Card(int sId) {
		SCard s = SCardCache.CACHE.get(sId);
		this.setAtt(s.getAtt());
		this.setCost(s.getCost());
		this.setHp(s.getHp());
		this.setSid(s.getSid());
		this.setType(s.getType());
		if (s.getSpellEffect() != null) {
			for (int eId : s.getSpellEffect()) {
				this.addSpellEffect(SEffectCache.CACHE.get(eId));
			}
		}
		if (s.getAureoleEffect() != null) {
			for (int eId : s.getAureoleEffect()) {
				this.addAureoleEffect(SEffectCache.CACHE.get(eId));
			}
		}
		if (s.getBattlecryEffect() != null) {
			for (int eId : s.getBattlecryEffect()) {
				this.addBattlecryEffect(SEffectCache.CACHE.get(eId));
			}
		}
		if (s.getDeathrattleEffect() != null) {
			for (int eId : s.getDeathrattleEffect()) {
				this.addDeathrattleEffect(SEffectCache.CACHE.get(eId));
			}
		}
		if (s.getInspireEffect() != null) {
			for (int eId : s.getInspireEffect()) {
				this.addInspireEffect(SEffectCache.CACHE.get(eId));
			}
		}
		this.setChooseone(s.getChooseoneEffect());
	}

	public void addEffect(SEffect se) {
		if (effects == null) {
			effects = new ArrayList<SEffect>();
		}
		effects.add(se);
	}

	public void addSpellEffect(SEffect se) {
		if (spellEffect == null) {
			spellEffect = new ArrayList<SEffect>();
		}
		spellEffect.add(se);
	}

	public void addAureoleEffect(SEffect se) {
		if (aureoleEffect == null) {
			aureoleEffect = new ArrayList<SEffect>();
		}
		aureoleEffect.add(se);
	}

	public void addBattlecryEffect(SEffect se) {
		if (battlecryEffect == null) {
			battlecryEffect = new ArrayList<SEffect>();
		}
		battlecryEffect.add(se);
	}

	public void addDeathrattleEffect(SEffect se) {
		if (deathrattleEffect == null) {
			deathrattleEffect = new ArrayList<SEffect>();
		}
		deathrattleEffect.add(se);
	}

	public void addInspireEffect(SEffect se) {
		if (inspireEffect == null) {
			inspireEffect = new ArrayList<SEffect>();
		}
		inspireEffect.add(se);
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

	public List<SEffect> getEffects() {
		return effects;
	}

	public void setEffects(List<SEffect> effects) {
		this.effects = effects;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public List<SEffect> getSpellEffect() {
		return spellEffect;
	}

	public void setSpellEffect(List<SEffect> spellEffect) {
		this.spellEffect = spellEffect;
	}

	public int[] getChooseone() {
		return chooseone;
	}

	public void setChooseone(int[] chooseone) {
		this.chooseone = chooseone;
	}

}
