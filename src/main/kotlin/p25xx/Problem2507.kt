package p25xx

import util.expect

fun main() {
    class Solution {
        fun smallestValue(n: Int): Int {
            var result = 0

            var t = n

            var d = 2
            while (d <= t / 2) {
                if (t % d == 0) {
                    t /= d
                    result += d
                } else {
                    d++
                }
            }

            result += t

            return result.takeIf { it < n }?.let { smallestValue(it) } ?: n
        }
    }

    expect {
        Solution().smallestValue(
            4
        )
    }
}