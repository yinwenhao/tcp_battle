package when_how.hero.battle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import io.netty.channel.ChannelHandlerContext;
import when_how.hero.battle.data.Battle;
import when_how.hero.common.MyException;
import when_how.hero.constants.MyErrorNo;

public class Manager {

	private static Map<Long, Battle> battles = new HashMap<Long, Battle>();

	private static Map<Long, ChannelHandlerContext> uidAndCtx = new HashMap<Long, ChannelHandlerContext>();

	private static Map<ChannelHandlerContext, Long> ctxAndUid = new HashMap<ChannelHandlerContext, Long>();

	private static Map<ChannelHandlerContext, Integer> sequence = new HashMap<ChannelHandlerContext, Integer>();

	private static Object lock = new Object();

	public static void putSequence(ChannelHandlerContext ctx, int sequence) {
		Manager.sequence.put(ctx, sequence);
	}

	/**
	 * 根据ctx获得请求顺序号，默认为0
	 * 
	 * @param ctx
	 * @return
	 */
	public static Integer getsequenceByCtx(ChannelHandlerContext ctx) {
		return sequence.containsKey(ctx) ? sequence.get(ctx) : 0;
	}

	public static ChannelHandlerContext getCtxByUid(long uid) {
		return uidAndCtx.get(uid);
	}

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
				sequence.remove(result);
			}
			return result;
		}
	}

	public static void putBattle(long[] uids, Battle battle) {
		for (long uid : uids) {
			battles.put(uid, battle);
		}
	}

	public static boolean inBattle(long[] uids) throws MyException {
		Battle b = null;
		for (long uid : uids) {
			if (b == null) {
				b = battles.get(uid);
			} else if (b != battles.get(uid)) {
				throw new MyException(MyErrorNo.wrongBattle);
			}
		}
		if (b == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean inBattle(long uid) {
		if (battles.containsKey(uid)) {
			return true;
		} else {
			return false;
		}
	}

	public static Battle removeBattle(long uid) {
		return battles.remove(uid);
	}

	/**
	 * 创建一个battle副本
	 * 
	 * @param uid
	 * @return
	 */
	public static Battle getBattleCopy(long uid) {
		Battle copy = new Battle();
		BeanUtils.copyProperties(battles.get(uid), copy);
		return copy;
	}

	/**
	 * 提交对battle的更新
	 * 
	 * @param uid
	 * @param battle
	 */
	public static void commit(long uid, Battle battle) {
		Battle b = battles.get(uid);
		if (b != null) {
			b.commit(battle);
		}
	}

}
