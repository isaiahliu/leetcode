package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMinimumDifference(root: TreeNode?): Int {
            var result = Int.MAX_VALUE

            fun TreeNode.dfs(): Array<TreeNode> {
                val nodePair = arrayOf(this, this)

                left?.dfs()?.also { (min, max) ->
                    nodePair[0] = min

                    result = result.coerceAtMost(`val` - max.`val`)
                }

                right?.dfs()?.also { (min, max) ->
                    nodePair[1] = max

                    result = result.coerceAtMost(min.`val` - `val`)
                }

                return nodePair
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().getMinimumDifference(
            TreeNode(
                236,
                TreeNode(
                    104,
                    null,
                    TreeNode(227)
                ),
                TreeNode(
                    701,
                    null,
                    TreeNode(911)
                )
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}