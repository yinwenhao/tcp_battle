package when_how.hero.battle.listener.impl;

import when_how.hero.battle.listener.CanBeTargetListener;

public class StealthCanBeTargetListener implements CanBeTargetListener {

	@Override
	public boolean canBeAttackTarget(boolean banBeTarget, boolean isMine) {
		return isMine && banBeTarget;
	}

	@Override
	public boolean canBeSpellTarget(boolean banBeTarget, boolean isMine) {
		return isMine && banBeTarget;
	}

}
