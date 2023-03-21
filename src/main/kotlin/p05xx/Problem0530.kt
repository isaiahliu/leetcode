package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMinimumDifference(root: TreeNode?): Int {
            var result = Int.MAX_VALUE

            fun TreeNode.dfs(): Pair<Int, Int> {
                var leftPair = `val` to `val`
                var rightPair = `val` to `val`

                left?.also {
                    leftPair = it.dfs()

                    result = result.coerceAtMost(`val` - leftPair.second)
                }

                right?.also {
                    rightPair = it.dfs()

                    result = result.coerceAtMost(rightPair.first - `val`)
                }

                return leftPair.first to rightPair.second
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