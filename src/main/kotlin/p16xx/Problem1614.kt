package p16xx

import util.expect

fun main() {
    class Solution {
        fun maxDepth(s: String): Int {
            var result = 0
            var depth = 0
            s.forEach {
                when (it) {
                    '(' -> {
                        depth++
                        result = result.coerceAtLeast(depth)
                    }

                    ')' -> {
                        depth--
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().maxDepth(
            ""
        )
    }
}

