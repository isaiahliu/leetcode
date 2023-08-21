package p07xx

import util.expect

fun main() {
    class Solution {
        fun countPrimeSetBits(left: Int, right: Int): Int {
            val primes = hashSetOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)

            return (left..right).count {
                Integer.bitCount(it) in primes
            }
        }
    }

    expect {
        Solution().countPrimeSetBits(
            6, 10
        )
    }
}