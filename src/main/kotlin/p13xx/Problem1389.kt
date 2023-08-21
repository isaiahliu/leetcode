package p13xx

import util.expect

fun main() {
    class Solution {
        fun createTargetArray(nums: IntArray, index: IntArray): IntArray {
            val result = arrayListOf<Int>()

            index.forEachIndexed { numIndex, resultIndex ->
                result.add(resultIndex, nums[numIndex])
            }

            return result.toIntArray()
        }
    }

    expect {
        Solution().createTargetArray(
            intArrayOf(0, 1, 2, 3, 4), intArrayOf(0, 1, 2, 2, 1)
        ).toList()
    }
}

