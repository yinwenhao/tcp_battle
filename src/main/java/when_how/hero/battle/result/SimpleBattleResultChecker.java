package when_how.hero.battle.result;

import java.util.List;

import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;

@Component("simpleBattleResultChecker")
public class SimpleBattleResultChecker implements BattleResultChecker {

	public void setBattleResultByLosers(Battle battle, List<Long> loserUserIds) {
		for (Player player : battle.getPlayers()) {
			for (long loser : loserUserIds) {
				if (player.getUserId() == loser) {
					player.setWinOrLose(BattleConstants.BATTLE_RESULT_LOSE);
				}
			}
			if (player.getWinOrLose() == BattleConstants.BATTLE_RESULT_DEFAULT) {
				player.setWinOrLose(BattleConstants.BATTLE_RESULT_WIN);
			}
		}
	}

	public void setBattleResultByWinners(Battle battle, List<Long> winnerUserIds) {
		for (Player player : battle.getPlayers()) {
			for (long winner : winnerUserIds) {
				if (player.getUserId() == winner) {
					player.setWinOrLose(BattleConstants.BATTLE_RESULT_WIN);
				}
			}
			if (player.getWinOrLose() == BattleConstants.BATTLE_RESULT_DEFAULT) {
				player.setWinOrLose(BattleConstants.BATTLE_RESULT_LOSE);
			}
		}
	}

	public void setBattleResultByLosersAndWinners(Battle battle, List<Long> loserUserIds, List<Long> winnerUserIds) {
		for (Player player : battle.getPlayers()) {
			for (long winner : winnerUserIds) {
				if (player.getUserId() == winner) {
					player.setWinOrLose(BattleConstants.BATTLE_RESULT_WIN);
				}
			}
			if (player.getWinOrLose() == BattleConstants.BATTLE_RESULT_DEFAULT) {
				player.setWinOrLose(BattleConstants.BATTLE_RESULT_LOSE);
			}
		}
	}

	@Override
	public boolean setBattleResult(Battle battle) {
		boolean end = false;
		for (Player player : battle.getPlayers()) {
			if (!player.getHero().isAlive()) {
				end = true;
				break;
			}
		}
		if (end) {
			for (Player player : battle.getPlayers()) {
				if (player.getHero().isAlive()) {
					// 胜利
					player.setWinOrLose(BattleConstants.BATTLE_RESULT_WIN);
				} else {
					// 失败
					player.setWinOrLose(BattleConstants.BATTLE_RESULT_LOSE);
				}
			}
		}
		return end;
	}

}
