package when_how.hero.battle.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import when_how.hero.battle.BattleConstants;

public class Player {

	private long userId;

	private int[] cardIds;

	private Hero hero;

	private int energy;

	private int energyMax;

	private Cards cards;

	private List<Servant> servants;

	private List<Card> hand;

	private boolean canChange;

	/**
	 * 0未定，1胜利，2失败
	 */
	private int winOrLose = 0;

	public Player(long uid, Hero hero, int[] cards) {
		this.setHero(hero);
		this.setUserId(uid);
		this.setEnergy(BattleConstants.ENERGY_INIT);
		this.setCardIds(cards);
		this.initCards();
		List<Servant> servants = new LinkedList<Servant>();
		this.setServants(servants);
		this.setHand(new ArrayList<Card>(BattleConstants.HAND_LIMIT));
		this.setCanChange(true);
	}

	public void changeCardsInhand(int[] changeIndex) {
		for (int i : changeIndex) {
			cards.add(hand.get(i));
		}
		cards.shuffle();
		for (int i : changeIndex) {
			hand.set(i, cards.getOneCard());
		}
		this.setCanChange(false);
	}

	/**
	 * 随从数量
	 * 
	 * @return
	 */
	public int getServantSize() {
		if (servants == null) {
			return 0;
		}
		return servants.size();
	}

	/**
	 * 移除一个随从
	 * 
	 * @param index
	 */
	public void removeServant(int index) {
		servants.remove(index);
	}

	/**
	 * 移除一个随从
	 * 
	 * @param s
	 */
	public void removeServant(Servant s) {
		servants.remove(s);
	}

	/**
	 * 增加随从
	 * 
	 * @param s
	 * @return 是否成功
	 */
	public boolean addServant(Servant s) {
		return addServant(servants.size(), s);
	}

	/**
	 * 增加随从
	 * 
	 * @param location
	 * @param s
	 * @return 是否成功
	 */
	public boolean addServant(int location, Servant s) {
		if (servants.size() >= BattleConstants.SERVANTS_NUM_MAX) {
			return false;
		}
		servants.add(location, s);
		return true;
	}

	/**
	 * 消耗能量
	 * 
	 * @param cost
	 * @return 是否成功
	 */
	public boolean useEnergy(int cost) {
		if (this.energy >= cost) {
			this.energy -= cost;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 重置能量
	 */
	public void resetEnergy() {
		this.setEnergy(this.getEnergyMax());
	}

	/**
	 * 增加能量上限
	 * 
	 * @param energy
	 *            增加的数量
	 * @param canUse
	 *            新增的能量是否可以立即使用
	 */
	public void addEnergy(int energy, boolean canUse) {
		this.energyMax += energy;
		if (this.energyMax > BattleConstants.ENERGY_LIMIT) {
			this.energyMax = BattleConstants.ENERGY_LIMIT;
		}
		if (canUse) {
			this.energy += energy;
			if (this.energy > this.energyMax) {
				this.energy = this.energyMax;
			}
		}
	}

	/**
	 * 摸牌
	 * 
	 * @param num
	 * 
	 * @return 是否死亡
	 */
	public boolean getCardsToHand(int num) {
		for (int i = 0; i < num; i++) {
			Card c = cards.getOneCard();
			if (c.getDamage() > 0) {
				// 牌库空了，受到伤害
				if (hero.decreaseHp(c.getDamage())) {
					// 英雄挂了
					return true;
				}
			}
			if (hand.size() < BattleConstants.HAND_LIMIT) {
				hand.add(c);
			}
		}
		return false;
	}

	/**
	 * 根据cardIds初始化cards
	 */
	public void initCards() {
		this.setCards(new Cards(cardIds));
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public List<Servant> getServants() {
		return servants;
	}

	public void setServants(List<Servant> servants) {
		this.servants = servants;
	}

	public int[] getCardIds() {
		return cardIds;
	}

	public void setCardIds(int[] cardIds) {
		this.cardIds = cardIds;
	}

	public Cards getCards() {
		return cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getEnergyMax() {
		return energyMax;
	}

	public void setEnergyMax(int energyMax) {
		this.energyMax = energyMax;
	}

	public boolean isCanChange() {
		return canChange;
	}

	public void setCanChange(boolean canChange) {
		this.canChange = canChange;
	}

	public int getWinOrLose() {
		return winOrLose;
	}

	public void setWinOrLose(int winOrLose) {
		this.winOrLose = winOrLose;
	}

}
