package when_how.hero.service.impl;

import io.netty.channel.ChannelHandlerContext;
import when_how.hero.battle.Manager;
import when_how.hero.battle.data.Battle;
import when_how.hero.battle.data.Player;
import when_how.hero.common.json.MyResponse;

public class BaseService {

	/**
	 * 
	 * @param battle
	 */
	protected void notifyAllPlayers(Battle battle) {
		for (Player p : battle.getPlayers()) {
			ChannelHandlerContext ctx = Manager.getCtxByUid(p.getUserId());
			if (ctx != null) {
				ctx.writeAndFlush(new MyResponse(battle, p.getUserId(), null));
			}
		}
	}

	/**
	 * 
	 * @param battle
	 * @param uid
	 */
	protected void notifyAllPlayersExceptUid(Battle battle, long uid) {
		for (Player p : battle.getPlayers()) {
			if (p.getUserId() != uid) {
				ChannelHandlerContext ctx = Manager.getCtxByUid(p.getUserId());
				if (ctx != null) {
					ctx.writeAndFlush(new MyResponse(battle, p.getUserId(), null));
				}
			}
		}
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
				ctx.writeAndFlush(new MyResponse(battle, p.getUserId(), battleReport));
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
	protected void notifyAllPlayersExceptUid(Battle battle, String battleReport, long uid) {
		for (Player p : battle.getPlayers()) {
			if (p.getUserId() != uid) {
				ChannelHandlerContext ctx = Manager.getCtxByUid(p.getUserId());
				if (ctx != null) {
					ctx.writeAndFlush(new MyResponse(battle, p.getUserId(), battleReport));
				}
			}
		}
	}

}
