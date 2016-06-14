package when_how.hero.remotememory;

import when_how.hero.battle.data.Battle;

public interface RemoteMemory {

	/**
	 * 将战斗结果放到远程存储
	 * 
	 * @param battle
	 */
	public void putBattleResult(Battle battle);

	public String getString(String keyHead, Object key);

}
