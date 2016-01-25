package when_how.hero.battle.data;

public class Entity {
	
	private int sid;
	
	private int att;

	private int hpMax;
	
	private int hp;
	
	/**
	 * 减少hp
	 * @param damage
	 * @return 是否死亡
	 */
	public boolean decreaseHp(int damage) {
		hp -= damage;
		if (hp <= 0) {
			return true;
		}
		return false;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}
	
}
