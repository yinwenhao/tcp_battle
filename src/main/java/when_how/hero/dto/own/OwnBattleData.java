package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Battle;

@JsonInclude(Include.NON_NULL)
public class OwnBattleData {
	
	private OwnPlayer[] players;
	
	public OwnBattleData(Battle battle, long uid) {
		this.players = new OwnPlayer[battle.getPlayers().length];
		for (int i=0; i<this.players.length; i++) {
			this.players[i] = new OwnPlayer(battle.getPlayers()[i], uid);
		}
	}

	public OwnPlayer[] getPlayers() {
		return players;
	}

	public void setPlayers(OwnPlayer[] players) {
		this.players = players;
	}

}
