package p03xx

import util.expect

fun main() {
    class Solution {
        fun maxCoins(nums: IntArray): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun walk(leftIndex: Int, rightIndex: Int): Int {
                val cacheKey = leftIndex to rightIndex

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val leftNum = nums.getOrElse(leftIndex) { 1 }
                val rightNum = nums.getOrElse(rightIndex) { 1 }

                var max = 0
                for (i in leftIndex + 1 until rightIndex) {
                    val base = nums[i] * leftNum * rightNum

                    max = max.coerceAtLeast(base + walk(leftIndex, i) + walk(i, rightIndex))
                }

                cache[cacheKey] = max
                return max
            }

            return walk(-1, nums.size)
        }
    }

    expect {
        Solution().maxCoins(
            intArrayOf(3, 1, 5, 8)
        )
    }
}

