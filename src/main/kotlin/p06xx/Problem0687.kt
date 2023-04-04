package p06xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestUnivaluePath(root: TreeNode?): Int {
            var result = 1

            fun TreeNode?.dfs(value: Int? = null): Int {
                this ?: return 0
                val leftSize = left.dfs(`val`)
                val rightSize = right.dfs(`val`)

                result = (leftSize + rightSize + 1).coerceAtLeast(result)

                return if (value == `val`) {
                    leftSize.coerceAtLeast(rightSize) + 1
                } else {
                    0
                }
            }

            root?.dfs()

            return result - 1
        }
    }

    measureTimeMillis {
        Solution().longestUnivaluePath(
            TreeNode(
                1,
                null,
                TreeNode(
                    1,
                    TreeNode(
                        1,
                        TreeNode(1),
                        TreeNode(1),
                    ),
                    TreeNode(
                        1,
                        TreeNode(1),
                        TreeNode(1)
                    ),
                )
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}