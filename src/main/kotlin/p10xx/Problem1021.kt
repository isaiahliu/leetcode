package p10xx

import util.expect

fun main() {
    class Solution {
        fun removeOuterParentheses(s: String): String {
            val str = StringBuilder()

            var level = 0

            s.forEach {
                when (it) {
                    '(' -> {
                        level++

                        if (level > 1) {
                            str.append(it)
                        }
                    }

                    ')' -> {
                        level--

                        if (level > 0) {
                            str.append(it)
                        }
                    }
                }
            }

            return str.toString()
        }
    }

    expect {
        Solution().removeOuterParentheses(
            ""
        )
    }
}