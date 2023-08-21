package p06xx

import util.expect

fun main() {
    class Solution {
        fun findMaxAverage(nums: IntArray, k: Int): Double {
            var sum = nums.take(k).sum()
            var max = sum

            for (i in k until nums.size) {
                sum -= nums[i - k]
                sum += nums[i]

                max = max.coerceAtLeast(sum)
            }

            return max.toDouble() / k
        }
    }

    expect {
        Solution().findMaxAverage(
            intArrayOf(), 1
        )
    }
}