package when_how.hero.dto.own;

import when_how.hero.battle.data.Equip;
import when_how.hero.sdata.cache.SCardCache;
import when_how.hero.sdata.domain.SCard;

public class OwnEquip {

	private int sid;

	private int hp;

	private int att;

	private int[] aureoleEffect; // 光环

	private int[] battlecryEffect; // 战吼

	private int[] deathrattleEffect; // 亡语

	private int[] inspireEffect; // 激励

	public OwnEquip(Equip equip) {
		this.sid = equip.getSid();
		this.hp = equip.getHp();
		this.att = equip.getAtt();
		SCard sCard = SCardCache.CACHE.get(equip.getSid());
		this.aureoleEffect = sCard.getAureoleEffect();
		this.battlecryEffect = sCard.getBattlecryEffect();
		this.deathrattleEffect = sCard.getDeathrattleEffect();
		this.inspireEffect = sCard.getInspireEffect();
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

}
