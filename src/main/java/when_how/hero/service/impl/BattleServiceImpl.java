package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.BattleService;


/**
 * @author when_how
 * 
 */

@Service("battleService")
public class BattleServiceImpl implements BattleService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public MyResponse useCard(long uid, int i, int location, int target, int chooseOne) {
		Battle battle = Manager.getBattle(uid);
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		
		return new MyResponse(MyErrorMessage.success);
	}

}
