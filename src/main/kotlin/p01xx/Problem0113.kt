package p01xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
            val result = arrayListOf<List<Int>>()
            val queue = LinkedList<Pair<TreeNode, List<Int>>>()

            root?.also { queue.add(it to listOf(it.`val`)) }

            found@ while (queue.isNotEmpty()) {
                for (i in queue.indices) {
                    val (top, route) = queue.pop()

                    if (top.left == null && top.right == null) {
                        if (top.`val` == targetSum) {
                            result.add(route)
                        }
                    }

                    top.left?.also {
                        val newRoute = route + it.`val`
                        it.`val` += top.`val`
                        queue.add(it to newRoute)
                    }
                    top.right?.also {
                        val newRoute = route + it.`val`
                        it.`val` += top.`val`
                        queue.add(it to newRoute)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().pathSum(
            null, 1
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

