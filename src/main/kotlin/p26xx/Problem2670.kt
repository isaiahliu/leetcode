package p26xx

import util.expect

fun main() {
    class Solution {
        fun distinctDifferenceArray(nums: IntArray): IntArray {
            val result = IntArray(nums.size)

            val set = hashSetOf<Int>()

            nums.indices.forEach {
                set += nums[it]

                result[it] += set.size
            }

            set.clear()
            nums.indices.reversed().forEach {
                result[it] -= set.size

                set += nums[it]
            }

            return result
        }
    }

    expect {
        Solution().distinctDifferenceArray(
            intArrayOf(2, 1, 3, 3, 2)
        )
    }
}
