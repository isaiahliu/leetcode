package p09xx

import util.expect

fun main() {
    class Solution {
        fun maxWidthRamp(nums: IntArray): Int {
            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int {
                if (startIndex > endIndex) {
                    return Int.MAX_VALUE
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return if (midNum > target) {
                    binarySearch(midIndex + 1, endIndex, target)
                } else {
                    midIndex.coerceAtMost(binarySearch(startIndex, midIndex - 1, target))
                }
            }

            var result = 0
            for (i in 1 until nums.size) {
                if (nums[i] >= nums[i - 1]) {
                    result = result.coerceAtLeast(i - binarySearch(0, i - 1, nums[i]))

                    nums[i] = nums[i - 1]
                }
            }

            return result
        }
    }

    expect {
        Solution().maxWidthRamp(
            intArrayOf(6, 0, 8, 2, 1, 5)
        )
    }
}
