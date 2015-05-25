package when_how.hero.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import when_how.hero.common.listener.IMySessionListener;

public class MySessionManager {

	private static Map<Integer, MySession> sessionMap = new ConcurrentHashMap<Integer, MySession>();

	private static ReadWriteLock lock = new ReentrantReadWriteLock(false);
	
	private static IMySessionListener mySessionListener = new DefaultMySessionListener();
	
	public static void setMySessionListener(IMySessionListener mySessionListener) {
		MySessionManager.mySessionListener = mySessionListener;
	}

	public static MySession getSession(int sessionId) {
		return sessionMap.get(sessionId);
	}
	
	public static void removeSession(MySession mySession) {
		sessionMap.remove(mySession.getSessionId());
	}

	public static int checkAndGetSessionId(int sessionId, int k) {
		try {
			lock.readLock().lock();
			if (sessionId != 0 && sessionMap.containsKey(sessionId)) {
				if (sessionMap.get(sessionId).getSessionTime() > System.currentTimeMillis()) {
					sessionMap.get(sessionId).setSessionTime(System.currentTimeMillis() + MyTcpConstants.mySessionTime);
					return sessionId;
				} else {
					// 增加mySession删除的监听
					mySessionListener.sessionDestroyed(sessionMap.get(sessionId));
					sessionMap.remove(sessionId);
				}
			}
			if (sessionId == 0) {
				sessionId = randomMySessionId(k);
				int i = 0;
				while (sessionMap.containsKey(sessionId) && sessionMap.get(sessionId).getSessionTime() > System.currentTimeMillis()) {
					sessionId = randomMySessionId(k);
					i++;
					if (i > 10) {
						sessionId = randomMySessionId(k + i);
					}
				}
			}
			MySession mySession = new MySession();
			mySession.setSessionId(sessionId);
			mySession.setSessionTime(System.currentTimeMillis() + MyTcpConstants.mySessionTime);
			sessionMap.put(sessionId, mySession);
			// 增加mySession创建的监听
			mySessionListener.sessionCreated(mySession);
			return sessionId;
		} finally {
			lock.readLock().unlock();
		}
	}

	private static int randomMySessionId(int k) {
		Long result = System.currentTimeMillis() << 8 + k % 256;
		if (result.hashCode() == 0) {
			return 1;
		}
		return result.hashCode();
	}

	/**
	 * 定时任务检查MySession是否过期
	 */
	public static void checkMySession() {
		try {
			List<Integer> list = new ArrayList<Integer>();
			lock.writeLock().lock();
			for (Entry<Integer, MySession> en : sessionMap.entrySet()) {
				if (en.getValue().getSessionTime() < System.currentTimeMillis()) {
					// mySession过期了，需要删除
					list.add(en.getKey());
				}
			}
			for (int sid : list) {
				// 增加mySession删除的监听
				mySessionListener.sessionDestroyed(sessionMap.get(sid));
				sessionMap.remove(sid);
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

}
