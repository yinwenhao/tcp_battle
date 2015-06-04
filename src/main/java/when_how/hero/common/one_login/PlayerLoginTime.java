package when_how.hero.common.one_login;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import when_how.hero.common.PropertiesConstants;
import when_how.hero.common.PropertiesKeys;
import when_how.hero.request.dto.UserDto;


public class PlayerLoginTime {

	/** 累计下线时间超过这个数就清空累计在线时间 */
	public static final int offlineHours = 5;

	public static final int fcmHours = 3;
	public static final int fcmHours2 = 5;

	/** 满3小时，收益减半 */
	public static final int fcmPersent = 2;
	/** 满5小时，收益为0 */
	public static final int fcmPersent2 = 0;

	public static final int fcmMark = 1;
	public static final int fcmMark2 = 2;

	// 存储在线用户的Map<player_id, 本次登录的时间(纳秒)>
	private static final ConcurrentMap<Integer, Long> onlineMap = new ConcurrentHashMap<Integer, Long>();

	private static final ConcurrentMap<Integer, Integer> fcmMap = new ConcurrentHashMap<Integer, Integer>();

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 设置用户防沉迷收益减少
	 * 
	 * @param playerId
	 * @param mark
	 */
	public static void addFcmPlayer(int playerId, int mark) {
		fcmMap.put(playerId, mark);
	}

	/**
	 * 移除防沉迷用户
	 * 
	 * @param playerId
	 */
	public static void removeFcmPlayer(int playerId) {
		fcmMap.remove(playerId);
	}

	public static int getResourceAfterFcm(int playerId, int resource) {
		if (resource <= 1) {
			// 至少为1
			return resource;
		}
		if (isFCMOn()) {
			// 防沉迷开启
			if (fcmMap.containsKey(playerId)) {
				if (fcmMap.get(playerId) == fcmMark) {
					// 收益减半
//					log.info(MyConstants.formatPlayerResourceAfterFcm(playerId,
//							resource, resource / 2));
					return resource / 2;
				} else if (fcmMap.get(playerId) == fcmMark2) {
					// 收益为0
//					log.info(MyConstants.formatPlayerResourceAfterFcm(playerId,
//							resource, 0));
					return 0;
				}
			}
		}
		return resource;
	}

	/**
	 * 0正常，1更新登录时间，-1已经被挤下线
	 * 
	 * @param userDto
	 * @return
	 */
	public static int checkLogin(UserDto userDto) {
		if (!onlineMap.containsKey(userDto.getId())) {
			return -1;
		} else {
			long currentLoginTime = onlineMap.get(userDto.getId());
			if (userDto.getLoginTime() - currentLoginTime < 0) {
				// 比较纳秒务必使用减法然后和0比，防止溢出
				return -1;
			} else if (userDto.getLoginTime() == currentLoginTime) {
				return 0;
			} else {
				onlineMap.put(userDto.getId(),
						userDto.getLoginTime());
				return 1;
			}
		}
	}

	/**
	 * 存放登录时间（纳秒）
	 * 
	 * @param userDto
	 */
	public static void putPlayerLoginTime(UserDto userDto) {
		onlineMap.put(userDto.getId(), userDto.getLoginTime());
	}

	/**
	 * 移除登录时间（纳秒）
	 * 
	 * @param userDto
	 */
	public static void removePlayerLoginTime(UserDto userDto) {
		if (userDto == null) {
			return;
		}
		if (!onlineMap.containsKey(userDto.getId())) {
			return;
		}
		if (onlineMap.get(userDto.getId()) <= userDto
				.getLoginTime()) {
			onlineMap.remove(userDto.getId());
		}
	}

	/**
	 * 防沉迷开关是否打开
	 * 
	 * @return
	 */
	public static boolean isFCMOn() {
		return "ON".equalsIgnoreCase(PropertiesConstants.getInstance()
				.getProperties(PropertiesKeys.FCM));
	}

}
