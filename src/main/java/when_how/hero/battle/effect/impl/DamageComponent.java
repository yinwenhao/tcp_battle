package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

public class DamageComponent implements MyComponent {

	private Player targetPlayer;

	private int damage;

	private int target;

	public DamageComponent(Player targetPlayer, int damage, int target) {
		this.targetPlayer = targetPlayer;
		this.damage = damage;
		this.target = target;
	}

	@Override
	public void display() throws MyException {
		Entity targetEntity;
		if (target == -1) {
			targetEntity = targetPlayer.getHero();
		} else {
			targetEntity = targetPlayer.getServants().get(target);
		}
		targetEntity.decreaseHp(damage);
	}

	@Override
	public void checkParam() throws MyException {

	}

}
