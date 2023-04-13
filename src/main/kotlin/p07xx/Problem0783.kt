package p07xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDiffInBST(root: TreeNode?): Int {
            var result = Int.MAX_VALUE

            fun TreeNode.dfs(rightMost: Boolean): Int {
                right?.dfs(false)?.also {
                    result = result.coerceAtMost(it - `val`)
                }
                left?.dfs(true)?.also {
                    result = result.coerceAtMost(`val` - it)
                }

                return if (rightMost) {
                    right?.dfs(true)
                } else {
                    left?.dfs(false)
                } ?: `val`
            }

            root?.dfs(false)

            return result
        }
    }

    measureTimeMillis {
        Solution().minDiffInBST(
            TreeNode(
                96,
                TreeNode(
                    12,
                    null,
                    TreeNode(
                        13,
                        null,
                        TreeNode(52, TreeNode(29))
                    )
                ),
                null
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}