package p13xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {
            class Walker(root: TreeNode?) {
                val stack = LinkedList<TreeNode>()

                init {
                    root?.also {
                        push(it)
                    }
                }

                private fun push(node: TreeNode) {
                    stack.push(node)

                    node.left?.also { push(it) }
                }

                fun next(): Int? {
                    return stack.poll()?.also {
                        it.right?.also { push(it) }
                    }?.`val`
                }
            }

            val walker1 = Walker(root1)
            val walker2 = Walker(root2)

            val result = arrayListOf<Int>()

            var value1 = walker1.next()
            var value2 = walker2.next()

            while (value1 != null && value2 != null) {
                if (value1 < value2) {
                    result.add(value1)
                    value1 = walker1.next()
                } else {
                    result.add(value2)
                    value2 = walker2.next()
                }
            }

            while (value1 != null) {
                result.add(value1)
                value1 = walker1.next()
            }

            while (value2 != null) {
                result.add(value2)
                value2 = walker2.next()
            }

            return result
        }
    }

    expect {
        Solution().getAllElements(
            null, null
        )
    }
}

