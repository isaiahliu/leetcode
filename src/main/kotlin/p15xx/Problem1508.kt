package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rangeSum(nums: IntArray, n: Int, left: Int, right: Int): Int {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            val sums = IntArray(n * (n + 1) / 2)
            var sumIndex = 0
            for (i in nums.indices) {
                val left = nums.getOrNull(i - 1) ?: 0
                for (j in i until nums.size) {
                    sums[sumIndex++] = nums[j] - left
                }
            }
            sums.sort()
            var result = 0L
            val m = 1000000007

            for (i in left - 1 until right) {
                result += sums[i]
                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().rangeSum(
            intArrayOf(1, 2, 3, 4), 4, 1, 2
        ).also { println(it) }
    }
}

