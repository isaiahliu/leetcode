package p01xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class BSTIterator(root: TreeNode?) {
        val stack = LinkedList<TreeNode>()

        private fun TreeNode.push() {
            stack.push(this)

            while (stack.peek().left != null) {
                stack.peek().left?.also { stack.push(it) }
            }
        }

        init {
            root?.push()
        }

        fun next(): Int {
            val top = stack.pop()

            top.right?.push()

            return top.`val`
        }

        fun hasNext(): Boolean {
            return stack.isNotEmpty()
        }
    }

    measureTimeMillis {
        BSTIterator(null)
    }.also { println("Time cost: ${it}ms") }
}

