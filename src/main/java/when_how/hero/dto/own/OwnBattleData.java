package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Battle;

@JsonInclude(Include.NON_NULL)
public class OwnBattleData {

	private OwnPlayer[] players;

	private int turn;

	private String battleReport;

	public OwnBattleData(Battle battle, long uid, String battleReport) {
		this.turn = battle.getTurn();
		this.battleReport = battleReport;
		this.players = new OwnPlayer[battle.getPlayers().length];
		for (int i = 0; i < this.players.length; i++) {
			OwnPlayer op = new OwnPlayer(battle.getPlayers()[i], uid);
			op.setIndex(i);
			this.players[i] = op;
		}
	}

	public OwnPlayer[] getPlayers() {
		return players;
	}

	public void setPlayers(OwnPlayer[] players) {
		this.players = players;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public String getBattleReport() {
		return battleReport;
	}

	public void setBattleReport(String battleReport) {
		this.battleReport = battleReport;
	}

}
