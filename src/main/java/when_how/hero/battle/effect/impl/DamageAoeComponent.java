package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

public class DamageAoeComponent implements MyComponent {

	private Player targetPlayer;

	private int damage;

	public DamageAoeComponent(Player targetPlayer, int damage) {
		this.targetPlayer = targetPlayer;
		this.damage = damage;
	}

	@Override
	public void display() throws MyException {
		for (int i = targetPlayer.getServantNum() - 1; i >= 0; i--) {
			if (targetPlayer.getServants().get(i).decreaseHp(damage)) {
				targetPlayer.removeServant(i);
			}
		}
	}

	@Override
	public void checkParam() throws MyException {

	}

}
