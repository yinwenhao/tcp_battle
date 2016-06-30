package when_how.hero.battle.data;

import java.util.ArrayList;
import java.util.List;

import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.domain.SCard;

public class Card {

	// 疲劳时的扣血量
	private int damage = 0;

	private int sid;

	private int hp;

	private int att;

	private int cost;

	private int type;

	private List<Integer> effects; // 自带的影响

	private List<Integer> spellEffect; // 法术效果

	private List<Integer> aureoleEffect; // 光环

	private List<Integer> battlecryEffect; // 战吼

	private List<Integer> deathrattleEffect; // 亡语

	private List<Integer> inspireEffect; // 激励

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
		if (s.getEffect() != null) {
			for (int eId : s.getEffect()) {
				this.addEffect(eId);
			}
		}
		if (s.getSpellEffect() != null) {
			for (int eId : s.getSpellEffect()) {
				this.addSpellEffect(eId);
			}
		}
		if (s.getAureoleEffect() != null) {
			for (int eId : s.getAureoleEffect()) {
				this.addAureoleEffect(eId);
			}
		}
		if (s.getBattlecryEffect() != null) {
			for (int eId : s.getBattlecryEffect()) {
				this.addBattlecryEffect(eId);
			}
		}
		if (s.getDeathrattleEffect() != null) {
			for (int eId : s.getDeathrattleEffect()) {
				this.addDeathrattleEffect(eId);
			}
		}
		if (s.getInspireEffect() != null) {
			for (int eId : s.getInspireEffect()) {
				this.addInspireEffect(eId);
			}
		}
		this.setChooseone(s.getChooseoneEffect());
	}

	public void addEffect(int se) {
		if (effects == null) {
			effects = new ArrayList<Integer>();
		}
		effects.add(se);
	}

	public void addSpellEffect(int se) {
		if (spellEffect == null) {
			spellEffect = new ArrayList<Integer>();
		}
		spellEffect.add(se);
	}

	public void addAureoleEffect(int se) {
		if (aureoleEffect == null) {
			aureoleEffect = new ArrayList<Integer>();
		}
		aureoleEffect.add(se);
	}

	public void addBattlecryEffect(int se) {
		if (battlecryEffect == null) {
			battlecryEffect = new ArrayList<Integer>();
		}
		battlecryEffect.add(se);
	}

	public void addDeathrattleEffect(int se) {
		if (deathrattleEffect == null) {
			deathrattleEffect = new ArrayList<Integer>();
		}
		deathrattleEffect.add(se);
	}

	public void addInspireEffect(int se) {
		if (inspireEffect == null) {
			inspireEffect = new ArrayList<Integer>();
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

	public List<Integer> getEffects() {
		return effects;
	}

	public void setEffects(List<Integer> effects) {
		this.effects = effects;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public List<Integer> getSpellEffect() {
		return spellEffect;
	}

	public void setSpellEffect(List<Integer> spellEffect) {
		this.spellEffect = spellEffect;
	}

	public int[] getChooseone() {
		return chooseone;
	}

	public void setChooseone(int[] chooseone) {
		this.chooseone = chooseone;
	}

}
