package p00xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        val map = mapOf(
            ')' to '(', ']' to '[', '}' to '{'
        )

        fun isValid(s: String): Boolean {
            val stack = LinkedList<Char>()

            for (c in s) {
                when (val left = map[c]) {
                    null -> {
                        stack.push(c)
                    }

                    else -> {
                        if (stack.isEmpty() || stack.pop() != left) {
                            return false
                        }
                    }
                }
            }

            return stack.isEmpty()
        }
    }

    expect {
        Solution().isValid("")
    }
}

