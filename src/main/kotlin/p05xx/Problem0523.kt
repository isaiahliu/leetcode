package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
            if (nums.size == 1) {
                return false
            }

            var sum = nums[0]

            for (i in 1 until nums.size) {
                if (nums[i] == 0 && nums[i - 1] == 0) {
                    return true
                }
                sum += nums[i]

            }

            val cache = hashMapOf<Pair<Int, Int>, Boolean>()

            fun find(startIndex: Int, endIndex: Int, sum: Int): Boolean {
                if (startIndex == endIndex) {
                    return false
                }

                if (sum % k == 0) {
                    return true
                }

                if (sum < k) {
                    return false
                }

                val cacheKey = startIndex to endIndex
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: false
                }

                val result = find(startIndex + 1, endIndex, sum - nums[startIndex]) || find(
                    startIndex,
                    endIndex - 1,
                    sum - nums[endIndex]
                )

                cache[cacheKey] = result

                return result
            }

            return find(0, nums.lastIndex, sum)
        }
    }

    measureTimeMillis {
        Solution().checkSubarraySum(intArrayOf(1, 0, 0, 2), 100).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}