package when_how.hero.dto.own;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import when_how.hero.battle.data.Player;

@JsonInclude(Include.NON_NULL)
public class OwnPlayer {

	private long userId;

	private OwnHero hero;

	private int energy;

	private int cardsNum;

	private int cardInHandNum;

	private OwnServant[] servants;

	private OwnCard[] hand;

	public OwnPlayer(Player player, long ownUid) {
		this.userId = player.getUserId();
		this.hero = new OwnHero(player.getHero());
		this.energy = player.getEnergy();
		this.cardsNum = player.getCards().size();
		this.cardInHandNum = player.getHand().size();
		if (player.getServants() != null && player.getServants().size() > 0) {
			this.servants = new OwnServant[player.getServants().size()];
			for (int i = 0; i < this.servants.length; i++) {
				this.servants[i] = new OwnServant(player.getServants().get(i));
			}
		}
		if (ownUid == player.getUserId() && player.getHand() != null
				&& player.getHand().size() > 0) {
			this.hand = new OwnCard[player.getHand().size()];
			for (int i = 0; i < this.hand.length; i++) {
				this.hand[i] = new OwnCard(player.getHand().get(i));
			}
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public OwnHero getHero() {
		return hero;
	}

	public void setHero(OwnHero hero) {
		this.hero = hero;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getCardsNum() {
		return cardsNum;
	}

	public void setCardsNum(int cardsNum) {
		this.cardsNum = cardsNum;
	}

	public OwnServant[] getServants() {
		return servants;
	}

	public void setServants(OwnServant[] servants) {
		this.servants = servants;
	}

	public OwnCard[] getHand() {
		return hand;
	}

	public void setHand(OwnCard[] hand) {
		this.hand = hand;
	}

	public int getCardInHandNum() {
		return cardInHandNum;
	}

	public void setCardInHandNum(int cardInHandNum) {
		this.cardInHandNum = cardInHandNum;
	}

}
