package p24xx

import util.expect

fun main() {
    class Solution {
        fun smallestEvenMultiple(n: Int): Int {
            return n shl (n % 2)
        }
    }

    expect {
        Solution().smallestEvenMultiple(
            5
        )

    }
}