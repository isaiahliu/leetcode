package p35xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray, k: Int): Int {
            return nums.sum() % k
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(), 2
        )
    }
}
