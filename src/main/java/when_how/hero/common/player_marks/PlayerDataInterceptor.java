package when_how.hero.common.player_marks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataInterceptor {

	private static Map<Integer, Long> playerMarks = new ConcurrentHashMap<Integer, Long>();

	private static Map<Integer, Long> legionMarks = new ConcurrentHashMap<Integer, Long>();
	private static Map<Integer, Set<Integer>> legionMarksAlreadyDo = new ConcurrentHashMap<Integer, Set<Integer>>();

	public static void putMarkForPlayer(int playerId, long mark) {
		if (playerMarks.containsKey(playerId)) {
			playerMarks.put(playerId, playerMarks.get(playerId) | mark);
		} else {
			playerMarks.put(playerId, mark);
		}
	}

	public static void putMarkForLegion(int legionId, long mark) {
		if (legionMarks.containsKey(legionId)) {
			legionMarks.put(legionId, legionMarks.get(legionId) | mark);
		} else {
			legionMarks.put(legionId, mark);
		}
		if (legionMarksAlreadyDo.containsKey(legionId)) {
			legionMarksAlreadyDo.get(legionId).clear();
		}
	}

	/**
	 * 联盟标志弄的并不完善，可能会使有些玩家重复获得标志
	 * 
	 * @param playerId
	 * @param legionId
	 * @return
	 */
	public static long getMarkByPlayerIdAndLegionId(int playerId, int legionId) {
		long result = 0;
		if (playerMarks.containsKey(playerId)) {
			result = playerMarks.get(playerId);
			playerMarks.remove(playerId);
		}
		if (legionMarks.containsKey(legionId)) {
			if (legionMarksAlreadyDo.containsKey(legionId)
					&& legionMarksAlreadyDo.get(legionId).contains(playerId)) {
				// 这个标志处理过
			} else {
				result = result | legionMarks.get(legionId);
				if (legionMarksAlreadyDo.containsKey(legionId)) {
					legionMarksAlreadyDo.get(legionId).add(playerId);
				} else {
					Set<Integer> set = new HashSet<Integer>();
					set.add(playerId);
					legionMarksAlreadyDo.put(legionId, set);
				}
			}
		}
		return result;
	}

}
