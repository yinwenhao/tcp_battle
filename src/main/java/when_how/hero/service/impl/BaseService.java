package when_how.hero.service.impl;

import io.netty.channel.ChannelHandlerContext;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.common.json.MyResponse;

public class BaseService {
	
	protected void endBattle(Battle battle) {
		
	}
	
	protected String fillBattleResultByLose(long lose, Battle battle) {
		long[] win = new long[battle.getPlayers().length];
		int i = 0;
		for (Player p : battle.getPlayers()) {
			if (p.getUserId() != lose) {
				win[i] = p.getUserId();
				i++;
			}
		}
		return fillBattleResult(new long[] { lose }, win);
	}

	protected String fillBattleResultByWin(long win, Battle battle) {
		long[] lose = new long[battle.getPlayers().length];
		int i = 0;
		for (Player p : battle.getPlayers()) {
			if (p.getUserId() != win) {
				lose[i] = p.getUserId();
				i++;
			}
		}
		return fillBattleResult(new long[] { win }, lose);
	}

	protected String fillBattleResult(long[] win, long[] lose) {
		StringBuilder sb = new StringBuilder();
		sb.append("#win:");
		for (int i = 0; i < win.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(win[i]);
		}
		sb.append("#lose:");
		for (int i = 0; i < lose.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(lose[i]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param battle
	 * @param battleReport
	 * 
	 */
	protected void notifyAllPlayers(Battle battle, String battleReport) {
		for (Player p : battle.getPlayers()) {
			ChannelHandlerContext ctx = Manager.getCtxByUid(p.getUserId());
			if (ctx != null) {
				ctx.writeAndFlush(new MyResponse(battle, p.getUserId(),
						battleReport));
			}
		}
	}

	/**
	 * 
	 * @param battle
	 * @param battleReport
	 * @param uid
	 *            不通知这个uid
	 */
	protected void notifyAllPlayersExceptUid(Battle battle,
			String battleReport, long uid) {
		for (Player p : battle.getPlayers()) {
			if (p.getUserId() != uid) {
				ChannelHandlerContext ctx = Manager.getCtxByUid(p.getUserId());
				if (ctx != null) {
					ctx.writeAndFlush(new MyResponse(battle, p.getUserId(),
							battleReport));
				}
			}
		}
	}

}
