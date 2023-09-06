package p23xx

import util.expect

fun main() {
    class Solution {
        fun countAsterisks(s: String): Int {
            var count = 1

            var result = 0
            s.forEach {
                when (it) {
                    '|' -> count = count xor 1
                    '*' -> result += count
                }
            }

            return result
        }
    }

    expect {
        Solution().countAsterisks(
            ""
        )
    }
}