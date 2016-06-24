package when_how.hero.battle.effect.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

@Component("cardComponent")
@Scope("prototype")
public class CardComponent implements MyComponent {

	private Player targetPlayer;

	private int num;

	public CardComponent(Player targetPlayer, int num) {
		this.targetPlayer = targetPlayer;
		this.num = num;
	}

	@Override
	public void display() throws MyException {
		targetPlayer.getCardsToHand(num);
	}

	@Override
	public void checkParam() throws MyException {

	}

}
