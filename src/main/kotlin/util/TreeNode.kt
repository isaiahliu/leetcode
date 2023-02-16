package util

import java.util.*

class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    override fun toString(): String {
        val stack = LinkedList<TreeNode>()

        val result = arrayListOf<Int?>()

        var t: TreeNode? = this
        loop@ while (true) {
            t?.`val`?.also { result.add(it) }

            t = when {
                t?.left != null -> {
                    stack.push(t)

                    t.left
                }

                t?.right != null -> {
                    result.add(null)

                    t.right
                }

                stack.isNotEmpty() -> {
                    stack.pop().right
                }

                else -> {
                    break@loop
                }
            }
        }

        return "[${result.joinToString()}]"
    }
}