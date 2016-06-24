package when_how.hero.battle.result;

import java.util.List;

import when_how.hero.battle.data.Battle;

public interface BattleResultChecker {

	public void setBattleResultByLosers(Battle battle, List<Long> loserUserIds);

	public void setBattleResultByWinners(Battle battle, List<Long> winnerUserIds);

	public void setBattleResultByLosersAndWinners(Battle battle, List<Long> loserUserIds, List<Long> winnerUserIds);

	/**
	 * 检查是否战斗结束，并设置战斗结果
	 * 
	 * @param battle
	 * @return 是否战斗结束
	 */
	public boolean setBattleResult(Battle battle);

}
