package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.constants.MyErrorNo;

public class DamageRangeComponent implements MyComponent {

	private Player targetPlayer;

	private int damage;

	private int target;

	private int damageArround;

	public DamageRangeComponent(Player targetPlayer, int damage, int damageArround, int target) {
		this.targetPlayer = targetPlayer;
		this.damage = damage;
		this.target = target;
		this.damageArround = damageArround;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		Servant leftServant = null;
		Servant rightServant = null;
		targetServant.decreaseHp(damage);
		int left = target - 1;
		int right = target + 1;
		if (left >= 0) {
			// 左边有随从
			leftServant = targetPlayer.getServants().get(left);
			leftServant.decreaseHp(damageArround);
		}
		if (right < targetPlayer.getServantNum()) {
			// 右边有随从
			rightServant = targetPlayer.getServants().get(right);
			rightServant.decreaseHp(damageArround);
		}
		if (!targetServant.isAlive()) {
			targetPlayer.removeServant(targetServant);
		}
		if (!leftServant.isAlive()) {
			targetPlayer.removeServant(leftServant);
		}
		if (!rightServant.isAlive()) {
			targetPlayer.removeServant(rightServant);
		}
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}
