package when_how.hero.battle.data;

import java.util.Random;

import when_how.hero.battle.BattleConstants;

public class Battle {

	private Player[] players;

	private int turn;

	private Random ran = new Random();

	/**
	 * 战斗开始
	 */
	public void start() {
		turn = ran.nextInt(players.length);
		getPlayer(turn).getCardsToHand(BattleConstants.FIRST_SIDE_HAND_LIMIT);
		getPlayer(turn + 1).getCardsToHand(
				BattleConstants.SECOND_SIDE_HAND_LIMIT);
	}

	/**
	 * 回合开始
	 * 
	 * @return 是否死亡
	 */
	public boolean startTurn() {
		return getPlayer(turn).getCardsToHand(1);
	}

	/**
	 * 回合结束
	 */
	public void endTurn() {
		getPlayer(turn).getHero().getSkill().setCanUse(true);
		turn++;
	}

	public Player getTurnPlayer() {
		return getPlayer(turn);
	}

	private Player getPlayer(int t) {
		int i = t % players.length;
		return players[i];
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

}
