package p01xx

import util.expect

fun main() {
    class Solution {
        fun findPeakElement(nums: IntArray): Int {
            var leftIndex = 0
            var rightIndex = nums.lastIndex

            while (leftIndex < rightIndex) {
                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                if (nums[midIndex] < nums[midIndex + 1]) {
                    leftIndex = midIndex + 1
                } else {
                    rightIndex = midIndex
                }
            }

            return leftIndex
        }
    }

    expect {
        Solution().findPeakElement(
            intArrayOf(1)
        )
    }
}

