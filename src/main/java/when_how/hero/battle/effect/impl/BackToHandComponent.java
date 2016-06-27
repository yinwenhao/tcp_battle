package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.constants.MyErrorNo;

public class BackToHandComponent implements MyComponent {

	private Player targetPlayer;

	private int target;

	private int costChange;

	public BackToHandComponent(Player targetPlayer, int target, int costChange) {
		this.targetPlayer = targetPlayer;
		this.target = target;
		this.costChange = costChange;
	}

	@Override
	public void display() throws MyException {
		Servant targetServant = targetPlayer.getServants().get(target);
		Card card = new Card(targetServant.getSid());
		card.setCost(card.getCost() + costChange);
		targetPlayer.addCardToHand(card);
	}

	@Override
	public void checkParam() throws MyException {
		MyChecker.checkTargetPositive(target);
	}

}
