package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.constants.MyErrorNo;

public class SilenceComponent implements MyComponent {

	private Player targetPlayer;

	private int target;

	public SilenceComponent(Player targetPlayer, int target) {
		this.targetPlayer = targetPlayer;
		this.target = target;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		targetServant.beSilence();
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}
