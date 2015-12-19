package calculators;

import java.awt.Point;
import java.util.HashMap;

public class ModFib extends Sequence<Integer, Integer> {
	public int seed1;
	public int seed2;
	public int mod;
	
	public int lastKey;
	
	private boolean repeatingCache;
	private int repeatStart;
	
	private HashMap<Point, Integer> pairCache;
	
	public ModFib() {
		super();
		seed1 = 1;
		seed2 = 2;
		lastKey = 2;
		mod = 2;
		repeatingCache = false;
		reseed();
	}

	@Override
	public void reseed() {
		cache.put(1, seed1);
		cache.put(2, seed2);
		
		Point pair = new Point(seed1, seed2);
		
		pairCache = new HashMap<Point, Integer>();
		pairCache.put(pair, 1);
		
		lastKey = 2;
	}

	@Override
	protected Integer calc(Integer n) {
		if (lastKey >= n) {
			return cache.get(n);
		}
		else if (repeatingCache) {
			int repeatIndex = repeatStart + (n % numRepeating()) - 1;
			return cache.get(repeatIndex);
		}
		
		while (lastKey < n) {
			int prev1 = cache.get(lastKey);
			int prev2 = cache.get(lastKey - 1);
			
			int val = (prev1 + prev2) % mod;
			Point pair = new Point(prev1, val);
			
			cache.put(lastKey + 1, val);
			
			if (!pairCache.containsKey(pair)) {
				pairCache.put(pair, lastKey + 1);
			} else {
				repeatStart = pairCache.get(pair);
				repeatingCache = true;
				
				cache.remove(lastKey + 1);
				cache.remove(lastKey);
				lastKey --;

				int repeatIndex = repeatStart + (n % numRepeating()) - 1;
				return cache.get(repeatIndex);
			}
			lastKey ++;
		}
		
		return cache.get(n);
	}
	
	public Integer numRepeating() {
		if (!repeatingCache) {
			calc(Integer.MAX_VALUE);
		}
		return lastKey - repeatStart + 1;
	}
	
	public Integer numNonRepeating() {
		if (!repeatingCache) {
			calc(Integer.MAX_VALUE);
		}
		return repeatStart - 1;
	}
}
