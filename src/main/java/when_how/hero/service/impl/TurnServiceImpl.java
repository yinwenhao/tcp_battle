package when_how.hero.service.impl;

import org.springframework.stereotype.Service;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;
import when_how.hero.service.TurnService;

@Service("turnService")
public class TurnServiceImpl extends BaseService implements TurnService {

	@Override
	public MyResponse endTurn(long uid, int turn) throws MyException {
		Battle battle = Manager.getBattle(uid);
		if (turn != battle.getTurn()) {
			throw new MyException(MyErrorNo.wrongParam);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			throw new MyException(MyErrorNo.notYourTurn);
		}
		battle.endTurn();
		StringBuilder sb = new StringBuilder();
		if (battle.startTurn()) {

		}
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse concede(long uid) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
