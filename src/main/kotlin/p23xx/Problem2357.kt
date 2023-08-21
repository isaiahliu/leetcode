package p23xx

import util.expect

fun main() {
    class Solution {
        fun minimumOperations(nums: IntArray): Int {
            return nums.filter { it > 0 }.toSet().size
        }
    }

    expect {
        Solution().minimumOperations(
            intArrayOf(4, 1, 2, 7, 5, 3, 1)
        )
    }
}

