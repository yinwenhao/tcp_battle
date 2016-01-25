package when_how.hero.battle;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import when_how.hero.battle.data.Battle;

public class Manager {
	
	private static Map<Long, Battle> battles = new HashMap<Long, Battle>();
	
	private static Map<Long, ChannelHandlerContext> uidAndCtx = new HashMap<Long, ChannelHandlerContext>();
	
	private static Map<ChannelHandlerContext, Long> ctxAndUid = new HashMap<ChannelHandlerContext, Long>();
	
	private static Object lock = new Object();
	
	public static Long getUidByCtx(ChannelHandlerContext ctx) {
		return ctxAndUid.get(ctx);
	}
	
	public static void putCtxAndUid(ChannelHandlerContext ctx, Long uid) {
		synchronized (lock) {
			ctxAndUid.put(ctx, uid);
			uidAndCtx.put(uid, ctx);
		}
	}
	
	public static ChannelHandlerContext removeCtxByUid(Long uid) {
		synchronized (lock) {
			ChannelHandlerContext result = uidAndCtx.get(uid);
			if (result != null) {
				uidAndCtx.remove(uid);
				ctxAndUid.remove(result);
			}
			return result;
		}
	}

	public static void putBattle(long uid, Battle battle) {
		battles.put(uid, battle);
	}
	
	public static boolean inBattle(long uid1, long uid2) throws Exception {
		Battle b1 = battles.get(uid1);
		Battle b2 = battles.get(uid2);
		if (b1 == b2) {
			if (b1 != null) {
				return true;
			} else {
				return false;
			}
		} else if (b1 != null || b2 != null) {
			throw new Exception("wrong battle");
		}
		return false;
	}
	
	public static boolean inBattle(long uid) {
		if (battles.containsKey(uid)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Battle getBattle(long uid) {
		return battles.get(uid);
	}

}
