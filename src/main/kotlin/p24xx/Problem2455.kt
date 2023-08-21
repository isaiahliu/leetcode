package p24xx

import util.expect

fun main() {
    class Solution {
        fun averageValue(nums: IntArray): Int {
            return nums.filter { it % 6 == 0 }.takeIf { it.isNotEmpty() }?.let {
                it.sum() / it.size
            } ?: 0
        }
    }

    expect {
        Solution().averageValue(
            intArrayOf(1, 2, 5)
        )
    }
}
