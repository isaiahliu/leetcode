package p33xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun countValidSelections(nums: IntArray): Int {
            var sum = 0L
            val sums = LongArray(nums.size) {
                sum += nums[it]
                sum
            }

            sum = 0
            var result = 0

            for (i in nums.lastIndex downTo 0) {
                if (nums[i] != 0) {
                    sum += nums[i]
                } else {
                    result += maxOf(0, 2 - (sum - sums[i]).absoluteValue.toInt())
                }
            }

            return result
        }
    }

    expect {
        Solution().countValidSelections(
            intArrayOf(16, 13, 10, 0, 10, 6, 7, 8, 7)
        )
    }
}
