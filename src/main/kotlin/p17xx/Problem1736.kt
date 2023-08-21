package p17xx

import util.expect

fun main() {
    class Solution {
        fun maximumTime(time: String): String {
            var (h1, h2, _, m1, m2) = time.toCharArray()

            if (h1 == '?') {
                h1 = when (h2) {
                    in '4'..'9' -> '1'
                    else -> '2'
                }
            }

            if (h2 == '?') {
                h2 = when (h1) {
                    '2' -> '3'
                    else -> '9'
                }
            }

            if (m1 == '?') {
                m1 = '5'
            }

            if (m2 == '?') {
                m2 = '9'
            }

            return "${h1}${h2}:${m1}${m2}"
        }
    }

    expect {
        Solution().maximumTime(
            ""
        )
    }
}
