package p31xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun numberOfPermutations(n: Int, requirements: Array<IntArray>): Int {
            val m = 1000000007L

            fun c(n: Int, k: Int): Int {
                val count = minOf(k, n - k)

                val f = LinkedList<Int>()
                repeat(count) {
                    f.add(it + 1)
                }

                var result = 1L
                repeat(count) {
                    result *= n - it

                    while (f.isNotEmpty() && result % f.peek() == 0L) {
                        result /= f.poll()
                    }
                }

                result %= m

                return result.toInt()
            }

            val forcedSize = hashSetOf<Int>()
            val maxCounts = IntArray(n) { Int.MAX_VALUE }
            requirements.forEach { (end, count) ->
                maxCounts[end] = count
                forcedSize += end
            }

            if (maxCounts[0] in 1 until Int.MAX_VALUE) {
                return 0
            }

            for (i in n - 2 downTo 0) {
                maxCounts[i] = minOf(maxCounts[i], maxCounts[i + 1])
            }

            val dp = Array(n) { LongArray(maxCounts.last() + 1) }

            dp[0][0] = 1

            for (i in 1 until dp.size) {
                val current = dp[i]
                val forced = i in forcedSize
                val maxCount = maxCounts[i]

                for (num in i downTo 0) {
                    val greater = i - num
                    val less = num
                    for (newCount in maxOf(i - less, 0)..minOf(maxCount, greater)) {
                        val prevCountEnd = maxCount - newCount
                        val prevCountStart = if (forced) prevCountEnd else 0

                        for (prevCount in prevCountStart..prevCountEnd) {
                            current[newCount + prevCount] += 1L * dp[i - 1][prevCount] * c(greater, newCount) * c(less, i - newCount)
                            current[newCount + prevCount] %= m
                        }
                    }
                }

            }

            return dp.last().last().toInt()
        }
    }

    expect {
        Solution().numberOfPermutations(
            2,
            arrayOf(
                intArrayOf(1, 1),
            )
        )
    }
}
