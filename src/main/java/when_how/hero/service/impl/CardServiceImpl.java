package when_how.hero.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Card;
import when_how.hero.battle.data.Equip;
import when_how.hero.battle.data.Player;
import when_how.hero.battle.data.Servant;
import when_how.hero.battle.effect.ComponentFactory;
import when_how.hero.battle.effect.MyComponent;
import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
import when_how.hero.constants.MyErrorNo;
import when_how.hero.service.CardService;

/**
 * @author when_how
 * 
 */

@Service("cardService")
public class CardServiceImpl extends BaseService implements CardService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ComponentFactory componentFactory;

	@Override
	public MyResponse useCard(long uid, int targetPlayerIndex, int i,
			int location, int target, int chooseOne) throws MyException {
		Battle battle = Manager.getBattle(uid);
		if (!battle.isStart()) {
			return new MyResponse(MyErrorNo.notYourTurn);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			return new MyResponse(MyErrorNo.notYourTurn);
		}

		StringBuilder sb = new StringBuilder();
		Card card = player.getHand().get(i);
		if (!player.useEnergy(card.getCost())) {
			// 能量不足
			return new MyResponse(MyErrorNo.notEnoughEnergy);
		}

		if (card.getChooseone() != null) {
			// 抉择
			card = new Card(card.getChooseone()[chooseOne]);
		}

		// 卡牌效果
		if (card.getType() == BattleConstants.CARD_TYPE_SERVANT) {
			if (player.getServantSize() >= BattleConstants.SERVANTS_NUM_MAX) {
				// 随从数量限制
				return new MyResponse(MyErrorNo.servantNumberLimit);
			}
			// 随从牌，随从需要先占个位置（防止召唤类的战吼把位置占光）
			Servant servant = new Servant(card);
			player.addServant(location, servant);
		}

		if (card.getBattlecryEffect() != null) {
			// 战吼
			MyComponent battlecry = componentFactory.getBattlecryComposite(
					card.getBattlecryEffect(), battle, location, target);
			battlecry.display();
		}

		if (card.getType() == BattleConstants.CARD_TYPE_SPELL) {
			// TODO 法术牌

		} else if (card.getType() == BattleConstants.CARD_TYPE_EQUIP) {
			// 装备牌
			Equip equip = new Equip(card);
			player.getHero().setEquip(equip);
		}

		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse changeCardsInHand(long uid, int[] changeIndex, int turn) {
		Battle battle = Manager.getBattle(uid);
		if (turn != battle.getTurn()) {
			return new MyResponse(MyErrorNo.wrongParam);
		}
		Player player = battle.getPlayerByUid(uid);
		if (!player.isCanChange()) {
			return new MyResponse(MyErrorNo.cannotChange);
		}
		if (changeIndex == null || changeIndex.length <= 0) {
			player.setCanChange(false);
			return new MyResponse(battle, uid);
		}
		for (int i : changeIndex) {
			if (i >= player.getHand().size()) {
				return new MyResponse(MyErrorNo.wrongParam);
			}
		}
		player.changeCardsInhand(changeIndex);
		battle.start();

		StringBuilder sb = new StringBuilder();
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse chooseCard(long uid, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
