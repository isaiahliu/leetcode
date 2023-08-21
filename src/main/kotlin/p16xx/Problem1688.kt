package p16xx

import util.expect

fun main() {
    class Solution {
        fun numberOfMatches(n: Int): Int {
            if (n == 1) {
                return 0
            }

            return n / 2 + numberOfMatches(n / 2 + n % 2)
        }
    }

    expect {
        Solution().numberOfMatches(
            14
        )
    }
}

