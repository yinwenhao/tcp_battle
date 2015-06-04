package when_how.hero.sdata.cache;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import when_how.hero.sdata.common.SDataLoader;
import when_how.hero.sdata.domain.ChargeItem;


public class ChargeItemCache extends SdataCache<Integer, ChargeItem>{
	
	private static ChargeItemCache self = null;
	
	private static Lock lock = new ReentrantLock();
	
	private ChargeItemCache() {
		List<Object[]> aList = SDataLoader.getModels(ChargeItem.class);
		for (Object[] a : aList) {
			put((Integer)a[0], (ChargeItem)a[1]);
		}
	}
	
	/**
	 * 获得单例
	 * @return
	 */
	public static ChargeItemCache getInstance() {
		if (self == null) {
			lock.lock();
			try {
				if (self == null) {
					self = new ChargeItemCache();
				}
			} finally {
				lock.unlock();
			}
		}
		return self;
	}
}
