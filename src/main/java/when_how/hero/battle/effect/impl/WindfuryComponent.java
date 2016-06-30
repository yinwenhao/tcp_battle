package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.battle.listener.impl.PlusAttackNumberListener;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.sdata.domain.SEffect;

public class WindfuryComponent implements MyComponent {

	private Player targetPlayer;

	private int attNumPlus;

	private int target;

	private SEffect se;

	public WindfuryComponent(SEffect se, Player targetPlayer, int target, int attNumPlus) {
		this.targetPlayer = targetPlayer;
		this.target = target;
		this.se = se;
		this.attNumPlus = attNumPlus;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		targetServant.addAttackNumberListener(new PlusAttackNumberListener(attNumPlus), se);
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}
