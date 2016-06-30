package when_how.hero.battle.listener.impl;

import when_how.hero.battle.listener.AttributeListener;

public class PlusAttributeListener implements AttributeListener {

	private int attPlus;

	private int hpPlus;

	public PlusAttributeListener(int attPlus, int hpPlus) {
		this.attPlus = attPlus;
		this.hpPlus = hpPlus;
	}

	@Override
	public int getAtt(int att) {
		return att + this.attPlus;
	}

	@Override
	public int getHp(int hp) {
		return hp + this.hpPlus;
	}

}
