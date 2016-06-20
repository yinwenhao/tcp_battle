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
	public MyResponse useCard(long uid, int targetPlayerIndex, int i, int location, int target, int chooseOne)
			throws MyException {
		Battle battle = Manager.getBattle(uid);
		if (!battle.isStart()) {
			throw new MyException(MyErrorNo.notYourTurn);
		}
		Player player = battle.getTurnPlayer();
		if (player.getUserId() != uid) {
			throw new MyException(MyErrorNo.notYourTurn);
		}

		StringBuilder sb = new StringBuilder();
		Card card = player.getHand().get(i);
		if (!player.useEnergy(card.getCost())) {
			// 能量不足
			throw new MyException(MyErrorNo.notEnoughEnergy);
		}

		if (card.getChooseone() != null) {
			// 抉择
			card = new Card(card.getChooseone()[chooseOne]);
		}

		// 卡牌效果
		if (card.getType() == BattleConstants.CARD_TYPE_SERVANT) {
			if (player.getServantSize() >= BattleConstants.SERVANTS_NUM_MAX) {
				// 随从数量限制
				throw new MyException(MyErrorNo.servantNumberLimit);
			}
			// 随从牌，随从需要先占个位置（防止召唤类的战吼把位置占光）
			Servant servant = new Servant(card);
			player.addServant(location, servant);
		}

		if (card.getBattlecryEffect() != null) {
			// 战吼
			MyComponent battlecry = componentFactory.getBattlecryComposite(card.getBattlecryEffect(), battle, location,
					target);
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
	public MyResponse changeCardsInHand(long uid, int[] changeIndex, int turn) throws MyException {
		Battle battle = Manager.getBattle(uid);
		if (turn != battle.getTurn()) {
			throw new MyException(MyErrorNo.wrongParam);
		}
		Player player = battle.getPlayerByUid(uid);
		if (!player.isCanChange()) {
			throw new MyException(MyErrorNo.cannotChange);
		}
		if (changeIndex == null || changeIndex.length <= 0) {
			player.setCanChange(false);
			return new MyResponse(battle, uid);
		}
		for (int i : changeIndex) {
			if (i >= player.getHand().size()) {
				throw new MyException(MyErrorNo.wrongParam);
			}
		}
		player.changeCardsInhand(changeIndex);
		battle.start();

		StringBuilder sb = new StringBuilder();
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse discoverOne(long uid, int i) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
