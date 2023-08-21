package p15xx

import util.expect

fun main() {
    class Solution {
        fun numIdenticalPairs(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.map {
                it * (it - 1) / 2
            }.sum()
        }
    }

    expect {
        Solution().numIdenticalPairs(
            intArrayOf(1)
        )
    }
}

