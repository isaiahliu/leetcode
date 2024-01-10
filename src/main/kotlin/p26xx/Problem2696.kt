package p26xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minLength(s: String): Int {
            val stack = LinkedList<Char>()

            s.forEach {
                if (it == 'B' && stack.peek() == 'A' || it == 'D' && stack.peek() == 'C') {
                    stack.poll()
                } else {
                    stack.push(it)
                }
            }

            return stack.size
        }
    }

    expect {
        Solution().minLength(
            ""
        )
    }
}