package when_how.hero.dao.entity;

import java.util.List;

public class UserDeck {

	private long userId;

	private int number;

	private String deckName;

	private List<Integer> deck;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public List<Integer> getDeck() {
		return deck;
	}

	public void setDeck(List<Integer> deck) {
		this.deck = deck;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
