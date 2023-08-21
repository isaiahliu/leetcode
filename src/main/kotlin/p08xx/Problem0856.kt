package p08xx

import util.expect

fun main() {
    class Solution {
        fun scoreOfParentheses(s: String): Int {
            val stack = IntArray(50)
            var index = 0
            s.forEach {
                when (it) {
                    '(' -> {
                        index++
                        stack[index] = 0
                    }

                    ')' -> {
                        var score = stack[index--] * 2
                        if (score == 0) {
                            score++
                        }

                        stack[index] += score
                    }
                }
            }

            return stack[0]
        }
    }

    expect {
        Solution().scoreOfParentheses(
            "()()"
        )

    }
}