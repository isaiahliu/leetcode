package p08xx

import util.expect

fun main() {
    class Solution {
        fun binaryGap(n: Int): Int {
            var result = -1

            var t = n
            while (t % 2 == 0) {
                t /= 2
            }

            t /= 2

            var zeroCount = 0
            while (t > 0) {
                if (t % 2 == 0) {
                    zeroCount++
                } else {
                    zeroCount = 0
                }
                result = result.coerceAtLeast(zeroCount)
                t /= 2
            }

            return result + 1
        }
    }

    expect {
        Solution().binaryGap(
            0b11
        )

    }
}