package p04xx

import util.expect

fun main() {
    class Solution {
        fun minMoves(nums: IntArray): Int {
            val min = nums.min()

            return nums.map { it - min }.sum()
        }
    }

    expect {
        Solution().minMoves(intArrayOf())
    }
}