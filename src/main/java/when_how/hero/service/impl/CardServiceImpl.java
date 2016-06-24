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
import when_how.hero.checker.MyChecker;
import when_how.hero.common.MyException;
import when_how.hero.common.json.MyResponse;
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
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);
		MyChecker.checkBattleStart(battle);
		MyChecker.checkPlayerInTurn(battle, uid);

		Player player = battle.getTurnPlayer();

		MyChecker.checkCardIndex(player, i);

		Card card = player.getHand().get(i);

		MyChecker.checkEnergy(player, card.getCost());

		StringBuilder sb = new StringBuilder();

		if (card.getChooseone() != null) {
			// 抉择
			card = new Card(card.getChooseone()[chooseOne]);
		}

		// 卡牌效果检查
		if (card.getType() == BattleConstants.CARD_TYPE_SERVANT) {
			MyChecker.checkServantNum(player);
		}

		MyComponent battlecry = null;
		if (card.getBattlecryEffect() != null) {
			// 战吼
			battlecry = componentFactory.getBattlecryComposite(card.getBattlecryEffect(), battle, location, target,
					targetPlayerIndex);
			battlecry.checkParam();
		}

		MyComponent spell = null;
		if (card.getType() == BattleConstants.CARD_TYPE_SPELL) {
			// 法术牌效果
			spell = componentFactory.getSpellComposite(card.getBattlecryEffect(), battle, target, targetPlayerIndex);
			spell.checkParam();
		}

		// 下面就不能抛错了，要改动数据了
		player.useEnergy(card.getCost());

		// 卡牌效果
		if (card.getType() == BattleConstants.CARD_TYPE_SERVANT) {
			// 随从牌，随从需要先占个位置（防止召唤类的战吼把位置占光）
			Servant servant = new Servant(card);
			player.addServant(location, servant);
		}

		if (battlecry != null) {
			// 战吼
			battlecry.display();
		}

		if (spell != null) {
			// 法术牌效果
			spell.display();
		}
		if (card.getType() == BattleConstants.CARD_TYPE_EQUIP) {
			// 装备牌
			Equip equip = new Equip(card);
			player.getHero().setEquip(equip);
		}

		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse changeCardsInHand(long uid, int[] changeIndex) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);

		Player player = battle.getPlayerByUid(uid);

		MyChecker.checkCanChangeHandCards(player);

		if (changeIndex != null && changeIndex.length > 0) {
			for (int i : changeIndex) {
				MyChecker.checkCardIndex(player, i);
			}
			// 下面就不能抛错了，要改动数据了
			player.changeCardsInhand(changeIndex);
		}

		// 下面就不能抛错了，要改动数据了
		player.setCanChange(false);
		battle.start();

		StringBuilder sb = new StringBuilder();
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse discoverOne(long uid, int chooseIndex) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);

		Player player = battle.getPlayerByUid(uid);

		MyChecker.checkDiscover(player);
		MyChecker.checkChooseIndex(player, chooseIndex);

		// 下面就不能抛错了，要改动数据了
		player.addCardToHand(player.getCardToChoose()[chooseIndex]);

		StringBuilder sb = new StringBuilder();
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

	@Override
	public MyResponse collectOne(long uid, int chooseIndex) throws MyException {
		Battle battle = Manager.getBattleCopy(uid);

		MyChecker.checkBattleNull(battle);

		Player player = battle.getPlayerByUid(uid);

		MyChecker.checkCollect(player);
		MyChecker.checkChooseIndex(player, chooseIndex);

		// 下面就不能抛错了，要改动数据了
		player.getCards().addAndShuffle(new Card(player.getCardToChoose()[chooseIndex]));

		StringBuilder sb = new StringBuilder();
		Manager.commit(uid, battle);
		notifyAllPlayersExceptUid(battle, sb.toString(), uid);
		return new MyResponse(battle, uid, sb.toString());
	}

}
