package p00xx

import java.util.*

fun main() {
    class Solution {
        fun longestValidParentheses(s: String): Int {
            var max = 0

            val stack = LinkedList<Int>()
            s.forEach {
                when (it) {
                    '(' -> {
                        stack.push(-1)
                    }

                    ')' -> {
                        var sum = 0
                        var match = false
                        while (!match && stack.isNotEmpty()) {
                            val t = stack.pop()
                            if (t == -1) {
                                match = true
                                sum += 2
                            } else {
                                sum += t
                            }
                        }

                        if (match) {
                            while (stack.isNotEmpty()) {
                                if (stack.peek() == -1) {
                                    break
                                } else {
                                    sum += stack.pop()
                                }
                            }

                            max = max.coerceAtLeast(sum)

                            stack.push(sum)
                        }
                    }
                }
            }

            return max
        }
    }

    Solution().longestValidParentheses("()(()")
}

