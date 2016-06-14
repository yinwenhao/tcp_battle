package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;

public class DamageComponent implements MyComponent {

	private Player player;

	private int[] param;

	private int target;

	public DamageComponent(Player player, int[] param, int target) {
		this.player = player;
		this.param = param;
		this.target = target;
	}

	@Override
	public void display() {
		Entity targetEntity;
		if (target == -1) {
			targetEntity = player.getHero();
		} else {
			targetEntity = player.getServants().get(target);
		}
		targetEntity.decreaseHp(param[0]);
	}

}
