package when_how.hero.battle.listener.impl;

import when_how.hero.battle.listener.AttackNumberListener;

public class PlusAttackNumberListener implements AttackNumberListener {

	private int attNum;

	public PlusAttackNumberListener(int attNum) {
		this.attNum = attNum;
	}

	@Override
	public int getAttNum(int attNum) {
		return attNum + this.attNum;
	}

}
