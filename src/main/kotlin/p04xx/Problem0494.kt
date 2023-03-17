package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTargetSumWays(nums: IntArray, target: Int): Int {
            val cache = hashMapOf(0 to 1)

            nums.forEach { num ->
                cache.toMap().also { cache.clear() }.forEach { (sum, count) ->
                    cache[sum + num] = (cache[sum + num] ?: 0) + count
                    cache[sum - num] = (cache[sum - num] ?: 0) + count
                }
            }

            return cache[target] ?: 0
        }
    }

    measureTimeMillis {
        Solution().findTargetSumWays(
            intArrayOf(1, 1, 1, 1, 1), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}