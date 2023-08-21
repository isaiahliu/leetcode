package p03xx

import util.expect

fun main() {
    class Solution {
        fun isPowerOfFour(n: Int): Boolean {
            return n > 0 && 0x40000000 % n == 0 && 0x55555555 and n == n
        }
    }

    expect {
        Solution().isPowerOfFour(
            16
        )
    }
}

