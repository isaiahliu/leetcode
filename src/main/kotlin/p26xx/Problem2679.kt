package p26xx

import util.expect

fun main() {
    class Solution {
        fun matrixSum(nums: Array<IntArray>): Int {
            nums.forEach {
                it.sort()
            }

            var result = 0
            for (index in nums[0].indices) {
                var max = Int.MIN_VALUE

                nums.forEach {
                    max = max.coerceAtLeast(it[index])
                }

                result += max
            }

            return result
        }
    }

    expect {
        Solution().matrixSum(
            arrayOf()
        )
    }
}
