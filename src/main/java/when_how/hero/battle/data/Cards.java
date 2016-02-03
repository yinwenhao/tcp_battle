package when_how.hero.battle.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {

	private List<Card> cards = new ArrayList<Card>();

	private Random ran = new Random();

	private int damage = 0;

	public Cards() {
	}

	public Cards(int[] cards) {
		for (int c : cards) {
			add(new Card(c));
		}
		shuffle();
	}

	/**
	 * 摸一张牌
	 * 
	 * @return
	 */
	public Card getOneCard() {
		if (cards.size() <= 0) {
			// 牌库已经没牌了
			damage++;
			Card result = new Card();
			result.setDamage(damage);
			return result;
		}
		return cards.remove(cards.size() - 1);
	}

	/**
	 * 随机抽取一张牌
	 */
	public Card randomCardAndGet() {
		return cards.remove(ran.nextInt());
	}

	/**
	 * 随机展示一张牌，不抽取
	 */
	public Card randomCard() {
		return cards.get(ran.nextInt());
	}

	/**
	 * 洗牌
	 */
	public void shuffle() {
		int index;
		Card c;
		for (int i = cards.size(); i > 1; i--) {
			index = ran.nextInt(i);
			c = cards.get(index);
			cards.set(index, cards.get(i - 1));
			cards.set(i - 1, c);
		}
	}

	/**
	 * 将牌加入牌库，放在牌库顶
	 * 
	 * @param card
	 */
	public void add(Card card) {
		cards.add(card);
	}

	/**
	 * 将牌洗入牌库
	 * 
	 * @param card
	 */
	public void addAndShuffle(Card card) {
		add(card);
		shuffle();
	}

	/**
	 * 牌堆里牌的数量
	 * 
	 * @return
	 */
	public int size() {
		return cards.size();
	}

}
