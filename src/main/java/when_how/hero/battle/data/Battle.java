package when_how.hero.battle.data;

import java.util.Random;

import when_how.hero.battle.BattleConstants;

public class Battle {

	private Player[] players;

	private int turn;

	private Random ran = new Random();

	private boolean start;

	public Battle(Player[] players) {
		this.setPlayers(players);
		this.setTurn(0);
		this.setStart(false);
	}

	/**
	 * 战斗初始化
	 */
	public void init() {
		turn = ran.nextInt(players.length);
		getPlayer(turn).getCardsToHand(BattleConstants.FIRST_SIDE_HAND_LIMIT);
		getPlayer(turn + 1).getCardsToHand(
				BattleConstants.SECOND_SIDE_HAND_LIMIT);
	}

	public void start() {
		for (Player p : players) {
			if (p.isCanChange()) {
				return;
			}
		}
		setStart(true);
		startTurn();
	}

	/**
	 * 回合开始
	 * 
	 * @return 是否死亡
	 */
	public boolean startTurn() {
		getTurnPlayer().addEnergy(BattleConstants.ENERGY_PER_TURN, false);
		getTurnPlayer().resetEnergy();
		return getTurnPlayer().getCardsToHand(1);
	}

	/**
	 * 回合结束
	 */
	public void endTurn() {
		Hero hero = getPlayer(this.turn).getHero();
		// 英雄技能使用次数清零
		hero.getSkill().setUseNum(0);
		// 英雄攻击次数清零
		hero.setAttNum(0);

		// 随从攻击次数清零
		for (Servant s : getPlayer(this.turn).getServants()) {
			s.setAttNum(0);
			s.setSummoningSickness(false);
		}

		this.turn++;
	}

	public Player getPlayerByUid(long uid) {
		for (Player p : players) {
			if (p.getUserId() == uid) {
				return p;
			}
		}
		return null;
	}

	public int getTurnIndex() {
		return turn % players.length;
	}

	public Player getTurnPlayer() {
		return getPlayer(turn);
	}
	
	public Player getNextTurnPlayer() {
		return getPlayer(turn+1);
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

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

}
