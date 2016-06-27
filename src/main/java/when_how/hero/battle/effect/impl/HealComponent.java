package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

public class HealComponent implements MyComponent {

	private Player targetPlayer;

	private int healHp;

	private int target;

	public HealComponent(Player targetPlayer, int target, int healHp) {
		this.targetPlayer = targetPlayer;
		this.healHp = healHp;
		this.target = target;
	}

	@Override
	public void display() throws MyException {
		Entity targetEntity = targetPlayer.getServants().get(target);
		targetEntity.heal(healHp);
	}

	@Override
	public void checkParam() throws MyException {
	}

}
