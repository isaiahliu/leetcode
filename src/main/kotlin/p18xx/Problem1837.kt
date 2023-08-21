package p18xx

import util.expect

fun main() {
    class Solution {
        fun sumBase(n: Int, k: Int): Int {
            var t = n
            var result = 0

            while (t > 0) {
                result += t % k
                t /= k
            }

            return result
        }
    }

    expect {
        Solution().sumBase(
            34, 6
        )

    }
}
