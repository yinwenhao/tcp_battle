package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.common.MyErrorMessage;
import when_how.hero.common.json.MyResponse;
import when_how.hero.service.CardService;

/**
 * @author when_how
 * 
 */

@Service("cardService")
public class CardServiceImpl extends BaseService implements CardService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public MyResponse useCard(long uid, int targetPlayerIndex, int i,
			int location, int target, int chooseOne) {
		Battle battle = Manager.getBattle(uid);
		if (!battle.isStart()) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorMessage.notYourTurn);
		}

		StringBuilder sb = new StringBuilder();
		Card card = player.getHand().get(i);
		if (player.getEnergy() < card.getCost()) {
			// 能量不足
			return new MyResponse(MyErrorMessage.notEnoughEnergy);
		}

		// 卡牌效果
		if (card.getType() == BattleConstants.CARD_TYPE_SERVANT) {
			// 随从牌
			Servant servant = new Servant(card);
			player.getServants().add(location, servant);
			// TODO: 随从的特殊效果
		} else if (card.getType() == BattleConstants.CARD_TYPE_SPELL) {
			// 法术牌

		} else if (card.getType() == BattleConstants.CARD_TYPE_EQUIP) {
			// 装备牌

		}

		if (!player.useEnergy(card.getCost())) {
			// 能量不足
			return new MyResponse(MyErrorMessage.notEnoughEnergy);
		}
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse changeCardsInHand(long uid, int[] changeIndex, int turn) {
		Battle battle = Manager.getBattle(uid);
		if (turn != battle.getTurn()) {
			return new MyResponse(MyErrorMessage.wrongParam);
		}
		Player player = battle.getPlayerByUid(uid);
		if (!player.isCanChange()) {
			return new MyResponse(MyErrorMessage.cannotChange);
		}
		if (changeIndex == null || changeIndex.length <= 0) {
			player.setCanChange(false);
			return new MyResponse(battle, uid);
		}
		for (int i : changeIndex) {
			if (i >= player.getHand().size()) {
				return new MyResponse(MyErrorMessage.wrongParam);
			}
		}
		player.changeCardsInhand(changeIndex);
		battle.start();

		StringBuilder sb = new StringBuilder();
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

}
