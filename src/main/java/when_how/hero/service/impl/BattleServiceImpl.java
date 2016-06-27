package when_how.hero.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Hero;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.result.BattleResultChecker;
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
import when_how.hero.remotememory.RemoteMemory;
import when_how.hero.service.BattleService;

/**
 * @author when_how
 * 
 */

@Service("battleService")
public class BattleServiceImpl extends BaseService implements BattleService {

	private static final String split = "|";

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BattleResultChecker simpleBattleResultChecker;

	@Autowired
	private RemoteMemory redisRemoteMemory;

	@Override
	public MyResponse useHeroSkill(long uid, int targetPlayerIndex, int target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse heroAttack(long uid, int targetPlayerIndex, int target) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);
		MyChecker.checkBattleStart(battle);
		MyChecker.checkPlayerInTurn(battle, uid);
		MyChecker.checkTargetPlayerIndexAndCannotBeSelf(uid, battle, targetPlayerIndex);

		Player targetPlayer = battle.getPlayers()[targetPlayerIndex];

		MyChecker.checkTargetIndex(targetPlayer, target);

		Player player = battle.getTurnPlayer();
		Hero hero = player.getHero();

		MyChecker.checkCanAttack(hero);

		hero.addAttNum();

		StringBuilder sb = new StringBuilder();
		sb.append(battle.getTurnIndex());
		sb.append(split);
		sb.append(-1);
		sb.append(split);
		sb.append(targetPlayerIndex);
		sb.append(split);
		sb.append(target);
		sb.append(split);
		sb.append(hero.getAtt());
		sb.append(split);
		List<Integer> attackResult = doAttack(hero, targetPlayer, target, sb);
		// if (attackResult.get(0) == 1) {
		// // 自己挂了
		// }
		for (int j = 1; j < attackResult.size(); j++) {
			if (attackResult.get(j) == -1) {
				// 对方英雄挂了
			} else {
				// 对方随从挂了
				targetPlayer.removeServant(attackResult.get(j));
			}
		}
		if (simpleBattleResultChecker.setBattleResult(battle)) {
			// 战斗结束，战斗结果放redis
			redisRemoteMemory.putBattleResult(battle);
		}
		
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse servantAttack(long uid, int targetPlayerIndex, int servantIndex, int target) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);
		MyChecker.checkBattleStart(battle);
		MyChecker.checkPlayerInTurn(battle, uid);
		MyChecker.checkTargetPlayerIndexAndCannotBeSelf(uid, battle, targetPlayerIndex);

		Player targetPlayer = battle.getPlayers()[targetPlayerIndex];

		MyChecker.checkTargetIndex(targetPlayer, target);

		Player player = battle.getTurnPlayer();

		MyChecker.checkServantIndex(player, servantIndex);

		Servant servant = player.getServants().get(servantIndex);

		MyChecker.checkCanAttack(servant);

		servant.addAttNum();

		StringBuilder sb = new StringBuilder();
		sb.append(battle.getTurnIndex());
		sb.append(split);
		sb.append(servantIndex);
		sb.append(split);
		sb.append(targetPlayerIndex);
		sb.append(split);
		sb.append(target);
		sb.append(split);
		sb.append(servant.getAtt());
		sb.append(split);
		servant.addAttNum();

		List<Integer> attackResult = doAttack(servant, targetPlayer, target, sb);
		if (attackResult.get(0) == 1) {
			// 自己挂了
			player.removeServant(servantIndex);
		}
		for (int j = 1; j < attackResult.size(); j++) {
			if (attackResult.get(j) == -1) {
				// 对方英雄挂了
			} else {
				// 对方随从挂了
				targetPlayer.removeServant(attackResult.get(j));
			}
		}
		if (simpleBattleResultChecker.setBattleResult(battle)) {
			// 战斗结束，战斗结果放redis
			redisRemoteMemory.putBattleResult(battle);
		}
		
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	/**
	 * 攻击
	 * 
	 * @param entity
	 * @param targetPlayer
	 * @param target
	 * @return [自己挂没，挂了的目标1，挂了的目标2，挂了的目标。。。]
	 *         例如[0,2,3,4]表示自己没挂，目标2、3、4号挂了；[1,-1]表示自己挂了，目标英雄挂了
	 */
	private List<Integer> doAttack(Entity entity, Player targetPlayer, int target, StringBuilder sb) {
		List<Integer> result = new ArrayList<Integer>(2);
		Entity targetEntity;
		if (target == BattleConstants.TARGET_HERO) {
			targetEntity = targetPlayer.getHero();
		} else {
			targetEntity = targetPlayer.getServants().get(target);
		}
		sb.append(targetEntity.getAtt());
		sb.append(split);
		if (entity.decreaseHp(targetEntity.getAtt())) {
			// 自己挂了
			result.add(1);
		} else {
			result.add(0);
		}
		if (targetEntity.decreaseHp(entity.getAtt())) {
			// 目标挂了
			result.add(target);
		}
		return result;
	}

}
