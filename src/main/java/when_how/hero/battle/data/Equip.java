package when_how.hero.battle.data;

import java.util.List;

public class Equip {

	private int sid;

	private int hp;

	private int att;

	private List<Integer> deathrattleEffect; // 亡语

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

	public List<Integer> getDeathrattleEffect() {
		return deathrattleEffect;
	}

	public void setDeathrattleEffect(List<Integer> deathrattleEffect) {
		this.deathrattleEffect = deathrattleEffect;
	}

}
