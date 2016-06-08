package when_how.hero.battle.effect.impl;

import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.MyComponent;

public class SummonComponent implements MyComponent {

	private Player player;

	private int[] param;

	private int location;

	public SummonComponent(Player player, int[] param, int location) {
		this.player = player;
		this.param = param;
		this.location = location;
	}

	@Override
	public void display() {
		for (int i = 0; i < param[1]; i++) {
			Servant servant = new Servant(new Card(param[0]));
			player.addServant(location, servant);
		}
	}

}
