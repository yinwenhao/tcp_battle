package when_how.hero.battle.listener.impl;

import when_how.hero.battle.listener.DamageListener;

public class SetToDamageListener implements DamageListener {
	
	private int damage;
	
	public SetToDamageListener(int damage) {
		this.damage = damage;
	}

	@Override
	public int getDamage(int damage) {
		return this.damage;
	}

}
