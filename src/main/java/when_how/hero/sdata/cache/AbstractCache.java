package when_how.hero.sdata.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



public abstract class AbstractCache<K, V> implements ICache<K, V>{

	// 缓存Map
	private ConcurrentMap<K, CacheObj> cacheMap = new ConcurrentHashMap<K, CacheObj>();
	
	// 实体列表
	private Vector<V> list = new Vector<V>();
	
	// 超时时间
	private long timeout;
	
	@Override
	public V get(K key) {
		if (key != null) {
			CacheObj obj = (CacheObj) cacheMap.get(key);
			if (obj != null
					&& (obj.getTimestamp() == 0 || (System.currentTimeMillis() - obj.getCreateTime()) <= obj.getTimestamp())) {
				return obj.getValue();
			} else {
				cacheMap.remove(key);
				return null;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public void put(K key, V value) {
		cacheMap.put(key, new CacheObj(value));
		list.addElement(value);
	}
	
	@Override
	public void remove(K key) {
		cacheMap.remove(key);
		list.remove(cacheMap.get(key).value);
	}
	
	@Override
	public Vector<V> getModels() {
		return this.list;
	}

	public void clear() {
		cacheMap.clear();
		list.clear();
	}
	
	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * 返回当前Map的的一个拷贝
	 * @return
	 */
	public Map<K, V> getCacheMap() {
		Map<K, V> map = new LinkedHashMap<K, V>();
		Set<Entry<K, CacheObj>> entrySet = cacheMap.entrySet();
		for (Entry<K, CacheObj> entry : entrySet) {
			map.put(entry.getKey(), entry.getValue().getValue());
		}
		return map;
	}


	/**
	 * 缓存对象
	 */
	class CacheObj {
		// 时间戳
		private long timestamp;
		
		// 值
		private V value;
		
		// 存入时间
		private long createTime;
		
		public CacheObj(V value) {
			this.timestamp = timeout;
			this.createTime = System.currentTimeMillis();
			this.value = value;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public V getValue() {
			return value;
		}

		public long getCreateTime() {
			return createTime;
		}
		
		
	}

}
