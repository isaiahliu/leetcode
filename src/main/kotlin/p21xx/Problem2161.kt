package p21xx

import util.expect

fun main() {
    class Solution {
        fun pivotArray(nums: IntArray, pivot: Int): IntArray {
            val result = IntArray(nums.size)

            var leftIndex = 0
            var rightIndex = result.lastIndex
            for (index in nums.indices) {
                if (nums[index] < pivot) {
                    result[leftIndex++] = nums[index]
                }

                if (nums[nums.lastIndex - index] > pivot) {
                    result[rightIndex--] = nums[nums.lastIndex - index]
                }
            }

            while (leftIndex <= rightIndex) {
                result[leftIndex++] = pivot
            }

            return result
        }
    }

    expect {
        Solution().pivotArray(
            intArrayOf(), 1
        )
    }
}