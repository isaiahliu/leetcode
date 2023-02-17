package p01xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
            val queue = LinkedList<TreeNode>()

            root?.also { queue.add(it) }

            found@ while (queue.isNotEmpty()) {
                for (i in queue.indices) {
                    val top = queue.pop()

                    if (top.left == null && top.right == null) {
                        if (top.`val` == targetSum) {
                            return true
                        }
                    }

                    top.left?.also {
                        it.`val` += top.`val`
                        queue.add(it)
                    }
                    top.right?.also {
                        it.`val` += top.`val`
                        queue.add(it)
                    }

                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().hasPathSum(
            null, 1
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

