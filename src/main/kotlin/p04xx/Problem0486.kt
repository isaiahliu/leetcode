package p04xx

import util.expect

fun main() {
    class Solution {
        fun PredictTheWinner(nums: IntArray): Boolean {
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            val totalScore = nums.sum()

            fun dfs(leftIndex: Int, rightIndex: Int, remaining: Int): Int {
                if (leftIndex == rightIndex) {
                    return nums[leftIndex]
                }

                val cacheKey = leftIndex to rightIndex
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val leftNum = nums[leftIndex]
                var result = remaining - dfs(leftIndex + 1, rightIndex, remaining - leftNum)

                val rightNum = nums[rightIndex]
                result =
                    result.coerceAtLeast(remaining - dfs(leftIndex, rightIndex - 1, remaining - rightNum))

                cache[cacheKey] = result
                return result
            }

            val bestScore = dfs(0, nums.lastIndex, totalScore)

            return bestScore * 2 >= totalScore
        }
    }

    expect {
        Solution().PredictTheWinner(
            intArrayOf(1, 5, 2, 4, 6)
        )
    }
}