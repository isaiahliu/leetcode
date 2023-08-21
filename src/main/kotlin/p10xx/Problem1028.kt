package p10xx

import util.TreeNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun recoverFromPreorder(traversal: String): TreeNode? {
            var depth = 0
            var num = 0

            val stack = LinkedList<TreeNode>()
            stack.add(TreeNode(Int.MAX_VALUE))

            "$traversal-".forEach {
                if (it == '-' && num > 0) {
                    val node = TreeNode(num)

                    while (stack.size != depth + 1) {
                        stack.pollLast()
                    }

                    stack.peekLast().also {
                        if (it.left == null) {
                            it.left = node
                        } else {
                            it.right = node
                        }
                    }

                    stack.add(node)

                    num = 0
                    depth = 0
                }

                when (it) {
                    '-' -> {
                        depth++
                    }

                    else -> {
                        num *= 10
                        num += it - '0'
                    }
                }
            }

            return stack.peek().left
        }
    }

    expect {
        Solution().recoverFromPreorder(
            "1-2--3-5--6"
        )
    }
}
