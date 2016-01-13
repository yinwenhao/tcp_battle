package when_how.hero.mydbcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import when_how.hero.dao.entity.User;


public class DBCache {
	
	public static Map<Integer, User> cacheUser = new ConcurrentHashMap<Integer, User>();

}
