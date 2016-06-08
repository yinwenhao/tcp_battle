package when_how.hero.battle.data;

import java.util.List;

import when_how.hero.sdata.domain.SEffect;

public class Equip {

	private int sid;

	private int hp;

	private int att;

	private List<SEffect> deathrattleEffect; // 亡语

	public Equip(Card card) {
		this.sid = card.getSid();
		this.hp = card.getHp();
		this.att = card.getAtt();
		this.deathrattleEffect = card.getDeathrattleEffect();
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

	public List<SEffect> getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(List<SEffect> deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

}
