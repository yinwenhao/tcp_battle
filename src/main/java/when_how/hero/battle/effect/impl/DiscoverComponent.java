package when_how.hero.battle.effect.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

@Component("discoverComponent")
@Scope("prototype")
public class DiscoverComponent implements MyComponent {

	private Player targetPlayer;

	private int[] cardIds;

	public DiscoverComponent(Player targetPlayer, int[] cardIds) {
		this.targetPlayer = targetPlayer;
		this.cardIds = cardIds;
	}

	@Override
	public void display() throws MyException {
		targetPlayer.setCardToChoose(cardIds);
		targetPlayer.setCardToChooseType(BattleConstants.CHOOSE_TYPE_DISCOVER);
	}

	@Override
	public void checkParam() throws MyException {

	}

}
