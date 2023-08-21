package p08xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun backspaceCompare(s: String, t: String): Boolean {
            fun String.process(): String {
                val queue = LinkedList<Char>()

                forEach {
                    if (it == '#') {
                        queue.pollLast()
                    } else {
                        queue.add(it)
                    }
                }

                return String(queue.toCharArray())
            }

            return s.process() == t.process()
        }
    }

    expect {
        Solution().backspaceCompare(
            "", ""
        )
    }
}