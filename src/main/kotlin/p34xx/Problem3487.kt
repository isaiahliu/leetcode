package p34xx

import util.expect

fun main() {
    class Solution {
        fun maxSum(nums: IntArray): Int {
            return nums.toSet().filter { it > 0 }.takeIf { it.isNotEmpty() }?.sum() ?: nums.max()
        }
    }

    expect {
        Solution().maxSum(
            intArrayOf()
        )
    }
}
