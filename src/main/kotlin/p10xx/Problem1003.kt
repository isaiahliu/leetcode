package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isValid(s: String): Boolean {
            val stack = LinkedList<Char>()

            s.forEach {
                when {
                    it == 'a' -> {
                        stack.push(it)
                    }

                    stack.isEmpty() -> {
                        return false
                    }

                    it == 'b' -> {
                        if (stack.peek() != 'a') {
                            return false
                        }

                        stack.pop()
                        stack.push(it)
                    }

                    it == 'c' -> {
                        if (stack.peek() != 'b') {
                            return false
                        }

                        stack.pop()
                    }
                }
            }

            return stack.isEmpty()
        }
    }

    expect {
        Solution().isValid(
            ""
        )
    }
}
