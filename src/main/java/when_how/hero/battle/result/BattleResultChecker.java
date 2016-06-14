package when_how.hero.battle.result;

import when_how.hero.battle.data.Battle;

public interface BattleResultChecker {

	/**
	 * 检查是否战斗结束，并设置战斗结果
	 * 
	 * @param battle
	 * @return 是否战斗结束
	 */
	public boolean setBattleResult(Battle battle);

}
