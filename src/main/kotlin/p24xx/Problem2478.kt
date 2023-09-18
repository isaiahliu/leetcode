package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun beautifulPartitions(s: String, k: Int, minLength: Int): Int {
            val primes = setOf('2', '3', '5', '7')

            if (s[0] !in primes || s.last() in primes) {
                return 0
            }

            if (k == 1) {
                return 1
            }

            val startIndices = (minLength until s.length - minLength + 1).filter {
                s[it] in primes && s[it - 1] !in primes
            }

            if (startIndices.size + 1 < k) {
                return 0
            }

            val m = 1000000007
            val dp = Array(startIndices.size) { hashMapOf<Int, Long>() }
            val sums = TreeMap<Int, MutableMap<Int, Long>>()

            dp[0][0] = 1L
            sums[startIndices[0]] = hashMapOf(1 to 1)

            for (i in 1 until startIndices.size) {
                val nextIndex = startIndices[i]
                val nextSum = sums.lastEntry().value.toMutableMap()

                dp[i][1] = 1
                nextSum[1] = (nextSum[1] ?: 0) + 1
                sums.floorEntry(nextIndex - minLength)?.value?.forEach { (key, value) ->
                    (key + 1).takeIf { it < k }?.also {
                        dp[i][key + 1] = value
                        nextSum[key + 1] = ((nextSum[key + 1] ?: 0) + value) % m
                    }
                }

                sums[nextIndex] = nextSum
            }

            return sums.lastEntry().value[k - 1]?.toInt() ?: 0
        }
    }

    expect {
        Solution().beautifulPartitions(
            "23542185131", 3, 2
        )
    }
}
