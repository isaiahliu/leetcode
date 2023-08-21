package p00xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun inorderTraversal(root: TreeNode?): List<Int> {
            val stack = LinkedList<TreeNode>()

            val result = arrayListOf<Int>()

            var t = root
            loop@ while (true) {
                t = when {
                    t?.left != null -> {
                        stack.push(t)

                        t.left
                    }

                    t?.right != null -> {
                        result.add(t.`val`)

                        t.right
                    }

                    stack.isNotEmpty() -> {
                        t?.`val`?.also { result.add(it) }

                        stack.pop().also {
                            result.add(it.`val`)
                        }.right
                    }

                    else -> {
                        t?.`val`?.also { result.add(it) }

                        break@loop
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().inorderTraversal(
            TreeNode(1)
        )
    }
}

