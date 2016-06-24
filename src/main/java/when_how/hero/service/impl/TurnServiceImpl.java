package when_how.hero.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.result.BattleResultChecker;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
import when_how.hero.remotememory.RemoteMemory;
import when_how.hero.service.TurnService;

@Service("turnService")
public class TurnServiceImpl extends BaseService implements TurnService {

	@Autowired
	private BattleResultChecker simpleBattleResultChecker;

	@Autowired
	private RemoteMemory redisRemoteMemory;

	@Override
	public MyResponse endTurn(long uid) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);
		
		MyChecker.checkBattleNull(battle);
		MyChecker.checkPlayerInTurn(battle, uid);
		
		battle.endTurn();
		StringBuilder sb = new StringBuilder();
		if (battle.startTurn()) {
			if (simpleBattleResultChecker.setBattleResult(battle)) {
				// 战斗结束，战斗结果放redis
				redisRemoteMemory.putBattleResult(battle);
			}
		}
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse concede(long uid) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);
		
		MyChecker.checkBattleNull(battle);
		
		List<Long> losers = new ArrayList<Long>(1);
		losers.add(uid);
		simpleBattleResultChecker.setBattleResultByLosers(battle, losers);
		redisRemoteMemory.putBattleResult(battle);
		
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, uid);
		return new MyResponse(battle, uid);
	}

}
