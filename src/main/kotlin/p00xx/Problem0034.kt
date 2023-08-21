package p00xx

import util.expect

fun main() {
    class Solution {
        fun searchRange(nums: IntArray, target: Int): IntArray {
            val result = intArrayOf(-1, -1)

            fun findLeft(leftIndex: Int, rightIndex: Int) {
                if (leftIndex > rightIndex) {
                    return
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                if (nums[midIndex] == target) {
                    result[0] = midIndex
                    findLeft(leftIndex, midIndex - 1)
                } else {
                    findLeft(midIndex + 1, rightIndex)
                }
            }

            fun findRight(leftIndex: Int, rightIndex: Int) {
                if (leftIndex > rightIndex) {
                    return
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                if (nums[midIndex] == target) {
                    result[1] = midIndex
                    findRight(midIndex + 1, rightIndex)
                } else {
                    findRight(leftIndex, midIndex - 1)
                }
            }

            fun findBoth(leftIndex: Int, rightIndex: Int) {
                if (leftIndex > rightIndex) {
                    return
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                if (nums[midIndex] == target) {
                    result[0] = midIndex
                    result[1] = midIndex

                    findLeft(leftIndex, midIndex - 1)
                    findRight(midIndex + 1, rightIndex)
                } else if (nums[midIndex] > target) {
                    findBoth(leftIndex, midIndex - 1)
                } else {
                    findBoth(midIndex + 1, rightIndex)
                }
            }

            if (nums.isNotEmpty() && target in nums[0]..nums[nums.lastIndex]) {
                findBoth(0, nums.lastIndex)
            }
            return result
        }
    }

    expect {
        Solution().searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).joinToString()
    }
}


