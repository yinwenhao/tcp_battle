package when_how.hero.sdata.cache;

import java.util.Collection;

public interface ICache<K, V> {

	V get(K key);

	void put(K key, V value);

	void remove(K key);

	void clear();

	Collection<V> getModels();

}
