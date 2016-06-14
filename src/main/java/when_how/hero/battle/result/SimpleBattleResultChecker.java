package when_how.hero.battle.result;

import org.springframework.stereotype.Component;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;

@Component("simpleBattleResultChecker")
public class SimpleBattleResultChecker implements BattleResultChecker {

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
