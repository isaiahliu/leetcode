package p23xx

import util.expect

fun main() {
    class Solution {
        fun secondsToRemoveOccurrences(s: String): Int {
            var zeroCount = 0
            var size = 0

            var result = 0
            s.forEach {
                if (it == '0') {
                    zeroCount++
                    size = (size - 1).coerceAtLeast(0)
                } else if (zeroCount > 0) {
                    result = result.coerceAtLeast(zeroCount + size)
                    size++
                }
            }

            return result
        }
    }

    expect {
        Solution().secondsToRemoveOccurrences(
            "0110101"
        )
    }
}