package when_how.hero.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BattleInitData {

	private long[] uid;

	private int[] heroId;

	private int[][] cards;

	public int[][] getCards() {
		return cards;
	}

	public void setCards(int[][] cards) {
		this.cards = cards;
	}

	public int[] getHeroId() {
		return heroId;
	}

	public void setHeroId(int[] heroId) {
		this.heroId = heroId;
	}

	public long[] getUid() {
		return uid;
	}

	public void setUid(long[] uid) {
		this.uid = uid;
	}

}
