package p08xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subtreeWithAllDeepest(root: TreeNode?): TreeNode? {
            var maxDepth = 0

            fun TreeNode.findMaxDepth(depth: Int) {
                maxDepth = maxDepth.coerceAtLeast(depth)

                left?.findMaxDepth(depth + 1)
                right?.findMaxDepth(depth + 1)
            }

            root?.findMaxDepth(1)

            fun TreeNode.findResult(depth: Int): TreeNode? {
                if (depth == maxDepth) {
                    return this
                }

                val leftTree = left?.findResult(depth + 1)
                val rightTree = right?.findResult(depth + 1)

                return if (leftTree != null && rightTree != null) {
                    this
                } else {
                    leftTree ?: rightTree
                }
            }

            return root?.findResult(1)
        }
    }

    measureTimeMillis {
        Solution().subtreeWithAllDeepest(
            null
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}