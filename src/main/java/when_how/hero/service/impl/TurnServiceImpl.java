package when_how.hero.service.impl;

import org.springframework.stereotype.Service;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.TurnService;

@Service("turnService")
public class TurnServiceImpl extends BaseService implements TurnService {

	@Override
	public MyResponse endTurn(long uid, int turn) {
		Battle battle = Manager.getBattle(uid);
		if (turn != battle.getTurn()) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		battle.endTurn();
		StringBuilder sb = new StringBuilder();
		if (battle.startTurn()) {

		}
		return new MyResponse(battle, uid, sb.toString());
	}

}
