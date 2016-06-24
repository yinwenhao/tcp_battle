package when_how.hero.checker;

import when_how.hero.battle.BattleConstants;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Entity;
import when_how.hero.battle.data.Player;
import when_how.hero.common.MyException;
import when_how.hero.constants.MyErrorNo;

public class MyChecker {

	/**
	 * 检查Battle是否为空
	 * 
	 * @param battle
	 * @throws MyException
	 */
	public static void checkBattleNull(Battle battle) throws MyException {
		if (battle == null) {
			throw new MyException(MyErrorNo.noBattle);
		}
	}

	/**
	 * 检查战斗是否开始
	 * 
	 * @param battle
	 * @throws MyException
	 */
	public static void checkBattleStart(Battle battle) throws MyException {
		if (!battle.isStart()) {
			throw new MyException(MyErrorNo.notYourTurn);
		}
	}

	/**
	 * 检查是否是这个玩家的回合
	 * 
	 * @param battle
	 * @param uid
	 * @throws MyException
	 */
	public static void checkPlayerInTurn(Battle battle, long uid) throws MyException {
		if (battle.getTurnPlayer().getUserId() != uid) {
			throw new MyException(MyErrorNo.notYourTurn);
		}
	}

	/**
	 * 检查targetPlayerIndex
	 * 
	 * @param battle
	 * @param targetPlayerIndex
	 * @throws MyException
	 */
	public static void checkTargetPlayerIndex(Battle battle, int targetPlayerIndex) throws MyException {
		if (targetPlayerIndex >= battle.getPlayers().length) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

	/**
	 * 检查targetPlayerIndex，并且不能是自己
	 * 
	 * @param uid
	 * @param battle
	 * @param targetPlayerIndex
	 * @throws MyException
	 */
	public static void checkTargetPlayerIndexAndCannotBeSelf(long uid, Battle battle, int targetPlayerIndex)
			throws MyException {
		checkTargetPlayerIndex(battle, targetPlayerIndex);
		Player targetPlayer = battle.getPlayers()[targetPlayerIndex];
		if (targetPlayer.getUserId() == uid) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

	/**
	 * 检查手牌序号i
	 * 
	 * @param player
	 * @param i
	 * @throws MyException
	 */
	public static void checkCardIndex(Player player, int i) throws MyException {
		if (i < 0) {
			throw new MyException(MyErrorNo.wrongParam);
		}
		if (player.getHand().size() >= i) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

	/**
	 * 检查能量是否足够
	 * 
	 * @param player
	 * @param cost
	 * @throws MyException
	 */
	public static void checkEnergy(Player player, int cost) throws MyException {
		if (player.getEnergy() < cost) {
			// 能量不足
			throw new MyException(MyErrorNo.notEnoughEnergy);
		}
	}

	/**
	 * 检查随从数量是否达到上限
	 * 
	 * @param player
	 * @throws MyException
	 */
	public static void checkServantNum(Player player) throws MyException {
		if (player.getServantNum() >= BattleConstants.SERVANTS_NUM_MAX) {
			// 随从数量限制
			throw new MyException(MyErrorNo.servantNumberLimit);
		}
	}

	/**
	 * 检查是否可以更换手牌
	 * 
	 * @param player
	 * @throws MyException
	 */
	public static void checkCanChangeHandCards(Player player) throws MyException {
		if (!player.isCanChange()) {
			throw new MyException(MyErrorNo.cannotChange);
		}
	}

	/**
	 * 检查目标随从（或英雄）序号target
	 * 
	 * @param targetPlayer
	 * @param target
	 * @throws MyException
	 */
	public static void checkTargetIndex(Player targetPlayer, int target) throws MyException {
		// target BattleConstants.TARGET_HERO 表示英雄
		if ((target >= targetPlayer.getServantNum() || target < 0) && target != BattleConstants.TARGET_HERO) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

	/**
	 * 检查随从序号
	 * 
	 * @param player
	 * @param servantIndex
	 * @throws MyException
	 */
	public static void checkServantIndex(Player player, int servantIndex) throws MyException {
		if (servantIndex >= player.getServantNum() || servantIndex < 0) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

	/**
	 * 检查是否能攻击
	 * 
	 * @param entity
	 * @throws MyException
	 */
	public static void checkCanAttack(Entity entity) throws MyException {
		if (!entity.isCanAttack()) {
			throw new MyException(MyErrorNo.cannotAttack);
		}
	}

	/**
	 * 检查是否有“发现”选牌
	 * 
	 * @param player
	 * @throws MyException
	 */
	public static void checkDiscover(Player player) throws MyException {
		if (player.getCardToChooseType() != BattleConstants.CHOOSE_TYPE_DISCOVER) {
			throw new MyException(MyErrorNo.noDiscover);
		}
	}

	/**
	 * 检查是否有“收藏”选牌
	 * 
	 * @param player
	 * @throws MyException
	 */
	public static void checkCollect(Player player) throws MyException {
		if (player.getCardToChooseType() != BattleConstants.CHOOSE_TYPE_COLLECT) {
			throw new MyException(MyErrorNo.noCollect);
		}
	}

	/**
	 * 检查选择卡牌时chooseIndex
	 * 
	 * @param player
	 * @param chooseIndex
	 * @throws MyException
	 */
	public static void checkChooseIndex(Player player, int chooseIndex) throws MyException {
		if (chooseIndex < 0 || chooseIndex >= player.getCardToChoose().length) {
			throw new MyException(MyErrorNo.wrongParam);
		}
	}

}
