package p12xx

import util.expect

fun main() {
    class Solution {
        fun findNumbers(nums: IntArray): Int {
            return nums.count { it.toString().length % 2 == 0 }
        }
    }

    expect {
        Solution().findNumbers(
            intArrayOf()
        )
    }
}
