package p03xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isValidSerialization(preorder: String): Boolean {
            val stack = LinkedList<Boolean>()
            stack.push(true)

            for (it in preorder.split(",")) {
                when {
                    stack.isEmpty() -> {
                        return false
                    }

                    it == "#" -> {
                        while (stack.isNotEmpty()) {
                            if (!stack.pop()) {
                                stack.push(true)
                                break
                            }
                        }
                    }

                    else -> {
                        stack.push(false)
                    }
                }
            }

            return stack.isEmpty()
        }
    }

    expect {
        Solution().isValidSerialization(
            "9,3,4,#,#,1,#,#,#,2,#,6,#,#"
        )
    }
}

