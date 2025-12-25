package p34xx

import util.expect

fun main() {
    class Solution {
        fun countPartitions(nums: IntArray): Int {
            return (nums.size - 1) * (1 - nums.sum() % 2)
        }
    }

    expect {
        Solution().countPartitions(
            intArrayOf()
        )
    }
}
