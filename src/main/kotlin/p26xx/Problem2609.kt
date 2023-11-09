package p26xx

import util.expect

fun main() {
    class Solution {
        fun findTheLongestBalancedSubstring(s: String): Int {
            var result = 0

            var zero = 0
            var one = 0

            s.forEach {
                when {
                    it == '1' -> {
                        one++

                        result = result.coerceAtLeast(zero.coerceAtMost(one))
                    }

                    one > 0 -> {
                        one = 0
                        zero = 1
                    }

                    else -> {
                        zero++
                    }
                }
            }

            return result * 2
        }
    }

    expect {
        Solution().findTheLongestBalancedSubstring(
            ""
        )
    }
}