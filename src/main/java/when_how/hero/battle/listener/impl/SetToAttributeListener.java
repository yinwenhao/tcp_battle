package when_how.hero.battle.listener.impl;

import when_how.hero.battle.listener.AttributeListener;

public class SetToAttributeListener implements AttributeListener {

	private static final int noChange = -1;

	private int att;

	private int hp;

	public SetToAttributeListener(int att, int hp) {
		this.att = att;
		this.hp = hp;
	}

	@Override
	public int getAtt(int att) {
		if (this.att == SetToAttributeListener.noChange) {
			return att;
		}
		return this.att;
	}

	@Override
	public int getHp(int hp) {
		if (this.hp == SetToAttributeListener.noChange) {
			return hp;
		}
		return this.hp;
	}

}
