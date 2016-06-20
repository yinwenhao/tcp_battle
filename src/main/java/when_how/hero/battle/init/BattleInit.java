package when_how.hero.battle.init;

import when_how.hero.battle.data.Battle;
import when_how.hero.common.MyException;
import when_how.hero.dto.BattleInitData;

/**
 * @author when_how
 * 
 */

public interface BattleInit {

	/**
	 * 分配对战的玩家卡组，并初始化战斗
	 * 
	 * @param battleInitData
	 * @return
	 * @throws MyException
	 */
	public Battle init(BattleInitData battleInitData) throws MyException;

}
