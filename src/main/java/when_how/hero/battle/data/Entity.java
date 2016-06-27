package when_how.hero.battle.data;

public class Entity {

	private int sid;

	private int att;

	private int hpMax;

	private int hp;

	private int attNum; // 本回合中，已经攻击的次数

	/**
	 * 增加生命值和上限
	 * 
	 * @param num
	 */
	public void addHpMax(int num) {
		if (num <= 0) {
			throw new RuntimeException("addHpMax a negative num");
		}
		this.hpMax += num;
		this.hp += num;
	}

	public void addAttNum() {
		this.attNum++;
	}

	/**
	 * 是否存活
	 * 
	 * @return
	 */
	public boolean isAlive() {
		return this.hp > 0;
	}

	/**
	 * 是否可以攻击，子类看情况重写这个方法
	 * 
	 * @return
	 */
	public boolean isCanAttack() {
		if (att <= 0) {
			return false;
		}
		if (attNum > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 减少hp
	 * 
	 * @param damage
	 * @return 是否死亡
	 */
	public boolean decreaseHp(int damage) {
		if (damage <= 0) {
			throw new RuntimeException("decreaseHp a negative num");
		}
		hp -= damage;
		if (hp <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 治疗hp
	 * 
	 * @param num
	 * @return 是否满血
	 */
	public boolean heal(int num) {
		if (num <= 0) {
			throw new RuntimeException("heal a negative num");
		}
		this.hp += num;
		if (this.hp >= this.hpMax) {
			this.hp = this.hpMax;
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

	public int getAttNum() {
		return attNum;
	}

	public void setAttNum(int attNum) {
		this.attNum = attNum;
	}

}
