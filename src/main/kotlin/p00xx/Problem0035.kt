package p00xx

import util.expect

fun main() {
    class Solution {
        fun searchInsert(nums: IntArray, target: Int): Int {
            val max = nums.size
            fun binarySearch(leftIndex: Int, rightIndex: Int): Int {
                if (leftIndex > rightIndex) {
                    return max
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                return if (nums[midIndex] >= target) {
                    midIndex.coerceAtMost(binarySearch(leftIndex, midIndex - 1))
                } else {
                    binarySearch(midIndex + 1, rightIndex)
                }
            }

            return binarySearch(0, nums.lastIndex)
        }
    }

    expect {
        Solution().searchInsert(intArrayOf(1, 3, 5, 6), 0)
    }
}


