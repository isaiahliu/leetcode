package p13xx

import util.expect

fun main() {
    class Solution {
        fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
            val sorted = nums.indices.sortedBy { nums[it] }

            val result = IntArray(nums.size)

            var t = 0
            for (i in 1 until sorted.size) {
                if (nums[sorted[i]] != nums[sorted[i - 1]]) {
                    t = i
                }

                result[sorted[i]] = t
            }

            return result
        }
    }

    expect {
        Solution().smallerNumbersThanCurrent(
            intArrayOf(8, 1, 1, 2, 3)
        )
    }
}

