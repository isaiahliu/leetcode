package p05xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
            subRoot ?: return true
            fun TreeNode?.sub(target: TreeNode?): Boolean {
                return when {
                    this == null && target == null -> true
                    target == null -> false
                    this == null -> false
                    this.`val` != target.`val` -> false
                    else -> left.sub(target.left) && right.sub(target.right)
                }

            }

            fun TreeNode?.dfs(): Boolean {
                return when {
                    this == null -> false
                    `val` == subRoot.`val` && sub(subRoot) -> true
                    else -> left.dfs() || right.dfs()
                }
            }

            return root.dfs()
        }
    }

    expect {
        Solution().isSubtree(
            TreeNode(
                3,
                TreeNode(
                    4,
                    TreeNode(1),
                    TreeNode(2)
                ),
                TreeNode(5)
            ), TreeNode(
                4,
                TreeNode(1),
                TreeNode(2)
            )
        )

    }
}