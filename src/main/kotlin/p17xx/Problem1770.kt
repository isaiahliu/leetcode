package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumScore(nums: IntArray, multipliers: IntArray): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(leftSkip: Int, rightSkip: Int): Int {
                if (leftSkip + rightSkip == multipliers.size) {
                    return 0
                }

                val cacheKey = leftSkip to rightSkip
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val multi = multipliers[leftSkip + rightSkip]

                val result = (nums[leftSkip] * multi + dfs(leftSkip + 1, rightSkip)).coerceAtLeast(
                    nums[nums.lastIndex - rightSkip] * multi + dfs(leftSkip, rightSkip + 1)
                )

                cache[cacheKey] = result
                return result
            }

            return dfs(0, 0)
        }
    }

    measureTimeMillis {
        Solution().maximumScore(
            intArrayOf(1, 2, 3), intArrayOf(3, 2, 1)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
