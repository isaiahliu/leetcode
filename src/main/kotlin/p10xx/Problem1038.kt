package p10xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun bstToGst(root: TreeNode?): TreeNode? {
            fun TreeNode.dfs(largerSum: Int): Int {
                val rightSum = right?.dfs(largerSum) ?: 0

                val leftSum = left?.dfs(largerSum + `val` + rightSum) ?: 0

                val result = `val` + leftSum + rightSum

                `val` += largerSum + rightSum

                return result
            }

            return root?.also { it.dfs(0) }
        }
    }

    measureTimeMillis {
        Solution().bstToGst(
            TreeNode(
                10,
                TreeNode(1),
                TreeNode(20)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
