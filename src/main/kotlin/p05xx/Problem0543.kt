package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun diameterOfBinaryTree(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(): Int {
                val leftDepth = left?.dfs() ?: 0
                val rightDepth = right?.dfs() ?: 0

                result = result.coerceAtLeast(leftDepth + rightDepth)

                return leftDepth.coerceAtLeast(rightDepth) + 1
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().diameterOfBinaryTree(
            TreeNode(
                1,
                TreeNode(
                    2,
                    TreeNode(4),
                    TreeNode(5),
                ),
                TreeNode(3)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}