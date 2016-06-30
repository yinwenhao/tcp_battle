package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

public class DamageAllComponent implements MyComponent {

	private Battle battle;

	private int damage;

	public DamageAllComponent(Battle battle, int damage) {
		this.battle = battle;
		this.damage = damage;
	}

	@Override
	public void display() throws MyException {
		for (Player player : battle.getPlayers()) {
			for (int i = player.getServantNum() - 1; i >= 0; i--) {
				if (player.getServants().get(i).decreaseHp(damage)) {
					player.removeServant(i);
				}
			}
		}
	}

	@Override
	public void checkParam() throws MyException {

	}

}
