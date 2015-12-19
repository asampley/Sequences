package calculators;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Stack;

public class Fibonacci extends Sequence<Integer, BigInteger> {
	
	@Override
	public void reseed() {
		if (cache.isEmpty()) {
			cache.put(1, BigInteger.valueOf(1));
			cache.put(2, BigInteger.valueOf(1));
			cache.put(3, BigInteger.valueOf(2));
		}
	}
	
	/**
	 * Returns the nth fibonacci number, based upon
	 * the seed values. The seed values default to 
	 * 0 and 1, but these can be changed.
	 * 
	 * Each value is equal to:
	 * f(n) = f(n-2) + f(n-1)
	 * @param n which fibonacci number to find
	 * @return 
	 */
	@Override
	public BigInteger calc(Integer n) {
		Stack<Integer> toCalc = new Stack<Integer>();
		toCalc.add(n);
		
		do {
			Integer a = toCalc.peek();
			
			//see if we have a already, to avoid recurring calculations
			if (cache.containsKey(a)) {
				toCalc.pop();
				if (toCalc.size() == 0) {
					break;
				}
				continue;
			}
			
			//calculate a / 2 + 1 for finding a
			if (!cache.containsKey(a / 2 + 1)) {
				toCalc.add(a / 2 + 1);
				continue;
			}
			//calculate a / 2 for finding a
			if (!cache.containsKey(a / 2)) {
				toCalc.add(a / 2);
				continue;
			}
			//if a is even, we also need to calculate a / 2 - 1 for finding a
			if (a % 2 == 0 && !cache.containsKey(a / 2 - 1)) {
				toCalc.add(a / 2 - 1);
				continue;
			}
			
			if (a % 2 == 0) {
				cache.put(a, cache.get(a / 2).multiply((cache.get(a / 2 - 1).add(cache.get(a / 2 + 1)))));
			} else {
				cache.put(a, (cache.get(a / 2).pow(2)).add(cache.get(a / 2 + 1).pow(2)));
			}
		} while (toCalc.size() > 0);
		
		return cache.get(n);
	}
}
