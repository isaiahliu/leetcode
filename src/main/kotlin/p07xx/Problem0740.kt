package p07xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun deleteAndEarn(nums: IntArray): Int {
            val counts = nums.toList().groupingBy { it }.eachCount()

            val dp = TreeMap<Int, Int>()

            counts.entries.sortedBy { it.key }.forEach { (num, count) ->
                val result1 = count * num + (dp.lowerEntry(num - 1)?.value ?: 0)
                val result2 = dp[num - 1] ?: 0

                dp[num] = result1.coerceAtLeast(result2)
            }

            return dp.lastEntry().value
        }
    }

    measureTimeMillis {
        Solution().deleteAndEarn(
            intArrayOf(2, 2, 3, 3, 3, 4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}