package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun combinationSum4(nums: IntArray, target: Int): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun combination(startIndex: Int, target: Int): Int {
                val cacheKey = startIndex to target
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0

                for (i in startIndex until nums.size) {
                    when {
                        nums[i] < target -> {
                            result += combination(startIndex, target - nums[i])
                        }

                        nums[i] == target -> {
                            result++
                        }
                    }
                }

                cache[cacheKey] = result

                return result
            }

            return combination(0, target)
        }
    }

    measureTimeMillis {
        Solution().combinationSum4(
            intArrayOf(1, 2, 3), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

