package p00xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun simplifyPath(path: String): String {
            val stack = LinkedList<String>()
            path.split("/").forEach {
                when (it) {
                    "", "." -> {}
                    ".." -> if (stack.isNotEmpty()) {
                        stack.pop()
                    }

                    else -> stack.push("/$it")
                }
            }

            return stack.reversed().joinToString("").ifEmpty { "/" }
        }
    }

    expect {
        Solution().simplifyPath("")
    }
}

