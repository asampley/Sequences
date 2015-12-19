package calculators;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Sequence<K, V> {
	protected HashMap<K, V> cache;
	protected boolean keepCache = true;
	
	public Sequence() {
		resetCache();
	}
	
	public abstract void reseed();
	
	public void resetCache() {
		cache = new HashMap<K, V>();
		reseed();
	}
	
	public V get(K n) {
		return calc(n);
	}
	
	protected abstract V calc(K n);
}
