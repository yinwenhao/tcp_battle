package when_how.hero.sdata.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class SdataCache<K, V> implements ICache<K, V> {
	
	private Map<K, V> map = new HashMap<K, V>();

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public void remove(K key) {
		map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<V> getModels() {
		return map.values();
	}
}
