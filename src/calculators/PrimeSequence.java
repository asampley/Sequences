package calculators;

import java.util.ArrayList;


public class PrimeSequence extends Sequence<Integer, Long> {
	private Integer lastKey;

	@Override
	public void reseed() {
		if (cache.isEmpty()) {
			cache.put(1, 2L);
			cache.put(2, 3L);
			lastKey = 2;
		} else {
			System.err.println("Failed to reseed non-empty cache");
		}
	}

	/**
	 * Returns the nth prime, based on this sequence:<br>
	 * 2, 3, 5, 7, 11<br>
	 * 1, 2, 3, 4, 5<br>
	 * so that 2 is at n = 1, etc.
	 * @param n
	 * @return nth prime
	 */
	@Override
	public Long calc(Integer n) {		
		if (lastKey >= n) {
			return cache.get(n);
		}

		Long i = cache.get(lastKey);

		while (lastKey < n) {
			i += 2;
			Long sqrti = (long) Math.sqrt(i);

			for (int k = 1; k <= lastKey; k ++) {
				Long prime = cache.get(k);
				if (i % prime == 0) {
					break;
				}
				if (prime > sqrti) {
					cache.put(lastKey + 1, i);
					lastKey ++;
					break;
				}
			}
		}

		return cache.get(n);
	}

	public boolean isPrime(Long x) {

		// if not in cache, calculate until past it, or at it.
		if (x >= cache.get(lastKey)) {
			while (x > cache.get(lastKey)) {
				calc(lastKey + 1);
			}
			if (x.equals(cache.get(lastKey))) {
				return true;
			} else {
				return false;
			}
		}

		// if in cache possibly, try to find it
		else {
			int topI = lastKey;
			int botI = 1;
			
			do {
				
				int midI = botI + (topI - botI) / 2;
				long midV = cache.get(midI);
				if (midV == x) {
					return true;
				} else if (midV < x) {
					botI = midI + 1;
				} else if (midV > x) {
					topI = midI - 1;
				}
				
			} while (botI <= topI);

			return false;
		}
	}
}
