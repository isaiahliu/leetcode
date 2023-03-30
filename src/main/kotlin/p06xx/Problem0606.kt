package p06xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun tree2str(root: TreeNode?): String {
            val result = StringBuilder()

            fun TreeNode.dfs() {
                result.append(`val`)

                if (left != null || right != null) {
                    result.append("(")
                    left?.dfs()
                    result.append(")")
                }

                right?.also {
                    result.append("(")
                    it.dfs()
                    result.append(")")
                }
            }

            root?.dfs()

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().tree2str(
            null
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}