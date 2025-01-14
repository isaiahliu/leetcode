package p30xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, k: Int): Int {
            return nums.count { it < k }
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(1), 1
        )
    }
}
