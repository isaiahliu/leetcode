package p03xx

import util.expect

fun main() {
    class Solution {
        fun nthSuperUglyNumber(n: Int, primes: IntArray): Int {
            val dp = LongArray(n)
            dp[0] = 1L

            val lastBased = IntArray(primes.size)
            val nextNums = LongArray(primes.size) { primes[it].toLong() }

            for (i in 1 until dp.size) {
                val nextNum = nextNums.min()
                dp[i] = nextNum

                nextNums.withIndex().filter { (_, n) -> n == nextNum }.forEach { (primeIndex, _) ->
                    nextNums[primeIndex] = dp[++lastBased[primeIndex]] * primes[primeIndex]
                }
            }

            return dp[n - 1].toInt()
        }
    }

    expect {
        Solution().nthSuperUglyNumber(
            5911, intArrayOf(
                2,
                3,
                5,
                7
            )
        )
    }
}

