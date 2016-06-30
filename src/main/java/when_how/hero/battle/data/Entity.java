package when_how.hero.battle.data;

import java.util.ArrayList;
import java.util.List;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.listener.AttackNumberListener;
import when_how.hero.battle.listener.AttributeListener;
import when_how.hero.battle.listener.CanBeTargetListener;
import when_how.hero.battle.listener.DamageListener;
import when_how.hero.sdata.domain.SEffect;

public class Entity {

	private int sid;

	private int att;

	private int hpMax;

	private int hp;

	private int attNum; // 本回合中，已经攻击的次数

	private List<Integer> effect; // 受到的影响

	private List<AttributeListener> attributeListeners;

	private List<DamageListener> damageListeners;

	private List<AttackNumberListener> attackNumberListeners;

	private List<CanBeTargetListener> canBeTargetListeners;

	private boolean taunt; // 嘲讽

	/**
	 * 能否成为攻击指定目标
	 * 
	 * @param player
	 *            这个随从的主人
	 * @param isMine
	 *            是否是攻击方的随从
	 * @return
	 */
	public boolean canBeAttackTarget(Player player, boolean isMine) {
		for (Servant servant : player.getServants()) {
			if (servant != this && servant.isTaunt()) {
				if (!isTaunt()) {
					return false;
				}
			}
		}
		return canBeTargetAfterListener(true, isMine, BattleConstants.TARGET_TYPE_ATTACK);
	}

	/**
	 * 能否成为法术指定目标
	 * 
	 * @param player
	 *            这个随从的主人
	 * @param isMine
	 *            是否是攻击方的随从
	 * @return
	 */
	public boolean canBeSpellTarget(Player player, boolean isMine) {
		return canBeTargetAfterListener(true, isMine, BattleConstants.TARGET_TYPE_SPELL);
	}

	public void addCanBeTargetListener(CanBeTargetListener canBeTargetListener, SEffect effect) {
		if (canBeTargetListeners == null) {
			canBeTargetListeners = new ArrayList<CanBeTargetListener>();
		}
		canBeTargetListeners.add(canBeTargetListener);
		addEffect(effect);
	}

	public void addAttackNumberListener(AttackNumberListener attackNumberListener, SEffect effect) {
		if (attackNumberListeners == null) {
			attackNumberListeners = new ArrayList<AttackNumberListener>();
		}
		attackNumberListeners.add(attackNumberListener);
		addEffect(effect);
	}

	public void addAttributeListener(AttributeListener attributeListener, SEffect effect) {
		if (attributeListeners == null) {
			attributeListeners = new ArrayList<AttributeListener>();
		}
		attributeListeners.add(attributeListener);
		addEffect(effect);
	}

	public void addDamageListener(DamageListener damageListener, SEffect effect) {
		if (damageListeners == null) {
			damageListeners = new ArrayList<DamageListener>();
		}
		damageListeners.add(damageListener);
		addEffect(effect);
	}

	public void addEffect(SEffect effect) {
		if (this.effect == null) {
			this.effect = new ArrayList<Integer>();
		}
		this.effect.add(effect.getSid());
	}

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
	 * 本回合可攻击的次数上限
	 * 
	 * @return
	 */
	public int getAttNumLimit() {
		return getAttNumAfterListener(BattleConstants.ATT_NUM_DEFAULT);
	}

	/**
	 * 是否可以攻击
	 * 
	 * @return
	 */
	public boolean isCanAttack() {
		if (att <= 0) {
			return false;
		}
		if (attNum >= getAttNumLimit()) {
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
		damage = getDamageAfterListener(damage);
		setHp(getHp() - damage);
		if (getHp() <= 0) {
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

	private int getDamageAfterListener(int damage) {
		if (damageListeners != null) {
			int result = damage;
			for (DamageListener a : damageListeners) {
				result = a.getDamage(result);
			}
			return result;
		}
		return damage;
	}

	private boolean canBeTargetAfterListener(boolean canBeTarget, boolean isMine, int type) {
		if (canBeTargetListeners != null) {
			boolean result = canBeTarget;
			for (CanBeTargetListener a : canBeTargetListeners) {
				if (type == BattleConstants.TARGET_TYPE_ATTACK) {
					result = a.canBeAttackTarget(result, isMine);
				} else {
					result = a.canBeSpellTarget(result, isMine);
				}
			}
			return result;
		}
		return canBeTarget;
	}

	private int getAttNumAfterListener(int attNum) {
		if (attackNumberListeners != null) {
			int result = attNum;
			for (AttackNumberListener a : attackNumberListeners) {
				result = a.getAttNum(result);
			}
			return result;
		}
		return attNum;
	}

	private int getHpAfterListener(int hp) {
		if (attributeListeners != null) {
			int result = hp;
			for (AttributeListener a : attributeListeners) {
				result = a.getHp(result);
			}
			return result;
		}
		return hp;
	}

	private int getAttAfterListener(int att) {
		if (attributeListeners != null) {
			int result = att;
			for (AttributeListener a : attributeListeners) {
				result = a.getAtt(result);
			}
			return result;
		}
		return att;
	}

	public int getHpMax() {
		return getHpAfterListener(hpMax);
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHp() {
		return getHpAfterListener(hp);
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
		return getAttAfterListener(att);
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

	public List<Integer> getEffect() {
		return effect;
	}

	public void setEffect(List<Integer> effect) {
		this.effect = effect;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt, SEffect effect) {
		this.taunt = taunt;
		addEffect(effect);
	}

}
