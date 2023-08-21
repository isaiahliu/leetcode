package p11xx

import util.expect

fun main() {
    class Solution {
        val cache = hashMapOf<Pair<Int, Int>, Long>()

        val m = 1000000007

        fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
            if (target < n || target > k * n) {
                return 0
            }

            if (n == 1) {
                return 1
            }

            val cacheKey = n to target
            if (cacheKey in cache) {
                return cache[cacheKey]?.toInt() ?: 0
            }

            var result = 0L

            repeat(k) {
                result += numRollsToTarget(n - 1, k, target - it - 1)
                result %= m
            }

            cache[cacheKey] = result

            return result.toInt()
        }
    }

    expect {
        Solution().numRollsToTarget(
            1, 6, 3
        )
    }
}