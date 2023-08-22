package p20xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            val sorted = nums.distinct().sorted()

            var left = 0
            var right = 0

            var max = 0
            while (right < sorted.size) {
                if (sorted[right] - sorted[left] < nums.size) {
                    max = max.coerceAtLeast(right - left + 1)
                    right++
                } else {
                    left++
                }
            }

            return nums.size - max
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(2, 5, 4),
        )
    }
}