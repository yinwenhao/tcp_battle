package when_how.hero.battle.data;

import java.util.List;

import when_how.hero.battle.BattleConstants;

public class Player {

	private long userId;
	
	private int[] cardIds;
	
	private Hero hero;
	
	private int energy;
	
	private Cards cards;
	
	private List<Servant> servants;
	
	private List<Card> hand;
	
	/**
	 * 摸牌
	 * @param num
	 * 
	 * @return 是否死亡
	 */
	public boolean getCardsToHand(int num) {
		for (int i=0; i<num; i++) {
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
	
}
