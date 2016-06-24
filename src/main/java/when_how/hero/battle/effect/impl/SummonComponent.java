package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;

public class SummonComponent implements MyComponent {

	private Player player;

	private int num;

	private int cardId;

	private int location;

	public SummonComponent(Player player, int num, int cardId, int location) {
		this.player = player;
		this.num = num;
		this.cardId = cardId;
		this.location = location;
	}

	@Override
	public void display() throws MyException {
		for (int i = 0; i < num; i++) {
			Servant servant = new Servant(new Card(cardId));
			player.addServant(location, servant);
		}
	}

	@Override
	public void checkParam() throws MyException {

	}

}
