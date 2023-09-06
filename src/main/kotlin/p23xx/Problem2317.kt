package p23xx

import util.expect

fun main() {
    class Solution {
        fun maximumXOR(nums: IntArray): Int {
            return nums.fold(0) { a, b -> a or b }
        }
    }

    expect {
        Solution().maximumXOR(
            intArrayOf(1, 2, 3, 9, 2)
        )
    }
}