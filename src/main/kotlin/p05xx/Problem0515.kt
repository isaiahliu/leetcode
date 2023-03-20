package p05xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestValues(root: TreeNode?): List<Int> {
            val result = arrayListOf<Int>()

            val level = LinkedList<TreeNode>()
            root?.also { level.add(it) }

            while (level.isNotEmpty()) {
                var max = Int.MIN_VALUE
                repeat(level.size) {
                    level.pop().also {
                        max = max.coerceAtLeast(it.`val`)
                        it.left?.also { level.add(it) }
                        it.right?.also { level.add(it) }
                    }

                }

                result.add(max)
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().largestValues(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}