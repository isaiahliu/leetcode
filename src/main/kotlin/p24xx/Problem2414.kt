package p24xx

import util.expect

fun main() {
    class Solution {
        fun longestContinuousSubstring(s: String): Int {
            var size = 0
            var pre = s[0]

            var result = 1
            s.forEach {
                if (pre + 1 == it) {
                    size++
                    result = result.coerceAtLeast(size)
                } else {
                    size = 1
                }

                pre = it
            }

            return result
        }
    }

    expect {
        Solution().longestContinuousSubstring(
            ""
        )
    }
}