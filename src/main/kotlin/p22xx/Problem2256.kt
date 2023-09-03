package p22xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minimumAverageDifference(nums: IntArray): Int {
            val total = nums.fold(0L) { a, b -> a + b }
            var result = 0
            var sum = 0L
            var max = Long.MAX_VALUE

            for (index in 0 until nums.lastIndex) {
                sum += nums[index]

                val diff = (sum / (index + 1) - (total - sum) / (nums.lastIndex - index)).absoluteValue

                if (diff < max) {
                    max = diff
                    result = index
                }
            }

            if (total / nums.size < max) {
                result = nums.lastIndex
            }

            return result
        }
    }

    expect {
        Solution().minimumAverageDifference(
            intArrayOf(5, 4, 3, 2, 1)
        )
    }
}