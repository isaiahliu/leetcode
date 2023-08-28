package p21xx

import util.expect

fun main() {
    class Solution {
        fun isSameAfterReversals(num: Int): Boolean {
            return num == 0 || num % 10 > 0
        }
    }

    expect {
        Solution().isSameAfterReversals(
            5
        )
    }
}