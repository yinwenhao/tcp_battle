package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.City;


public class CityCache extends SdataCache<Integer, City>{
	
	private static CityCache self = null;
	
	private static Lock lock = new ReentrantLock();
	
	private CityCache() {
		List<Object[]> aList = SDataLoader.getModels(City.class);
		for (Object[] a : aList) {
			put((Integer)a[0], (City)a[1]);
		}
	}
	
	/**
	 * 获得单例
	 * @return
	 */
	public static CityCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new CityCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}
