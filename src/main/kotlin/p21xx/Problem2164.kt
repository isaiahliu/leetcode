package p21xx

import util.expect

fun main() {
    class Solution {
        fun sortEvenOdd(nums: IntArray): IntArray {
            val result = IntArray(nums.size)

            (nums.indices step 2).sortedBy { nums[it] }.forEachIndexed { index, i ->
                result[index * 2] = nums[i]
            }

            (1 until nums.size step 2).sortedByDescending { nums[it] }.forEachIndexed { index, i ->
                result[index * 2 + 1] = nums[i]
            }

            return result
        }
    }

    expect {
        Solution().sortEvenOdd(
            intArrayOf(7, 9, 5, 8, 1, 3)
        )
    }
}