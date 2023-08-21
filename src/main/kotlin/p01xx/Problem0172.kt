package p01xx

import util.expect

fun main() {
    class Solution {
        fun trailingZeroes(n: Int): Int {
            var result = 0
            var t = n / 5

            while (t > 0) {
                result += t
                t /= 5
            }

            return result
        }
    }

    expect {
        Solution().trailingZeroes(30)
    }
}

