package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.battle.listener.impl.StealthCanBeTargetListener;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.sdata.domain.SEffect;

public class StealthComponent implements MyComponent {

	private Player targetPlayer;

	private int target;

	private SEffect se;

	public StealthComponent(SEffect se, Player targetPlayer, int target) {
		this.targetPlayer = targetPlayer;
		this.target = target;
		this.se = se;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		targetServant.addCanBeTargetListener(new StealthCanBeTargetListener(), se);
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}
