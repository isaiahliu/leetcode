package util

import java.util.*

class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null) {
    override fun toString(): String {
        val queue = LinkedList<TreeNode?>()

        val result = arrayListOf<Int?>()

        queue.add(this)

        while (queue.isNotEmpty()) {
            val size = queue.size

            repeat(size) {
                val next = queue.poll()

                result.add(next?.`val`)

                if (next?.left != null || next?.right != null) {
                    queue.add(next.left)
                    queue.add(next.right)
                }
            }
        }

        return "[${result.joinToString(",")}]"
    }
}