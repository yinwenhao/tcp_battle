package when_how.hero.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Hero;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.BattleService;

/**
 * @author when_how
 * 
 */

@Service("battleService")
public class BattleServiceImpl extends BaseService implements BattleService {

	private static final String split = "|";

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public MyResponse useHeroSkill(long uid, int targetPlayerIndex, int target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse heroAttack(long uid, int targetPlayerIndex, int target) {
		Battle battle = Manager.getBattle(uid);
		if (!battle.isStart()) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		if (targetPlayerIndex >= battle.getPlayers().length) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Player targetPlayer = battle.getPlayers()[targetPlayerIndex];
		if (targetPlayer.getUserId() == uid) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		if (target >= targetPlayer.getServantSize() || target < -1) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Hero hero = player.getHero();
		if (!hero.isCanAttack()) {
			return new MyResponse(MyErrorMessage.cannotAttack);
		}
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
		if (attackResult.get(0) == -1) {
			// 自己挂了
			// TODO: 游戏结束
			sb.append(fillBattleResultByLose(uid, battle));
		}
		for (int j = 1; j < attackResult.size(); j++) {
			if (attackResult.get(j) == -1) {
				// 对方英雄挂了
				// TODO: 游戏结束
				sb.append(fillBattleResultByWin(uid, battle));
			} else {
				// 对方随从挂了
				targetPlayer.removeServant(attackResult.get(j));
			}
		}
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse servantAttack(long uid, int targetPlayerIndex, int i,
			int target) {
		Battle battle = Manager.getBattle(uid);
		if (!battle.isStart()) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		if (i >= player.getServantSize()) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		if (targetPlayerIndex >= battle.getPlayers().length) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Player targetPlayer = battle.getPlayers()[targetPlayerIndex];
		if (targetPlayer.getUserId() == uid) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		if (target >= targetPlayer.getServantSize() || target < -1) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Servant servant = player.getServants().get(i);
		if (!servant.isCanAttack()) {
			return new MyResponse(MyErrorMessage.cannotAttack);
		}
		servant.addAttNum();

		StringBuilder sb = new StringBuilder();
		sb.append(battle.getTurnIndex());
		sb.append(split);
		sb.append(i);
		sb.append(split);
		sb.append(targetPlayerIndex);
		sb.append(split);
		sb.append(target);
		sb.append(split);
		sb.append(servant.getAtt());
		sb.append(split);
		servant.addAttNum();
		List<Integer> attackResult = doAttack(servant, targetPlayer, target, sb);
		if (attackResult.get(0) == -1) {
			// 自己挂了
			player.removeServant(i);
		}
		for (int j = 1; j < attackResult.size(); j++) {
			if (attackResult.get(j) == -1) {
				// 对方英雄挂了
				// TODO: 游戏结束
				sb.append(fillBattleResultByWin(uid, battle));
			} else {
				// 对方随从挂了
				targetPlayer.removeServant(attackResult.get(j));
			}
		}
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
	private List<Integer> doAttack(Entity entity, Player targetPlayer,
			int target, StringBuilder sb) {
		List<Integer> result = new ArrayList<Integer>(2);
		Entity targetEntity;
		if (target == -1) {
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
