# Sequences
Eclipse Project for calculating sequences (specifically Fibonacci)

The most intersting sequence in this program is the fibonacci sequence, which can be calculated in O(log(n)).
This operates on a trick involving multiplying previous fibonacci numbers. This can only really be done with
the sequence starting at 1 1, and actually requires starting the sequence with a third number to keep things 
from breaking down. However, the nth fibonacci number can now be calculated using a few numbers around the n/2th
fibonacci number, which are calculated from the n/4th, etc.

This results in some very fast calculations of very large fibonacci numbers. Note, this could also be done using
the golden ratio, but would require some very large exponents, as well as a very accurate representation of the
golden ratio.
