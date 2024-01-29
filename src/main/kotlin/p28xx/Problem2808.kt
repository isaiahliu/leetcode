package p28xx

import util.expect

fun main() {
    class Solution {
        fun minimumSeconds(nums: List<Int>): Int {
            return nums.indices.groupBy { nums[it] }.values.minOf {
                var result = (it[0] + nums.size - it[it.lastIndex]) / 2

                it.reduce { a, b ->
                    result = maxOf(result, (b - a) / 2)
                    b
                }

                result
            }
        }
    }
    expect {
        Solution().minimumSeconds(
            listOf(2, 1, 3, 3, 2)
        )
    }
}
