package p13xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun removeLeafNodes(root: TreeNode?, target: Int): TreeNode? {
            val head = TreeNode(0)
            head.left = root

            fun TreeNode.dfs(): Boolean {
                left?.also {
                    if (it.dfs()) {
                        left = null
                    }
                }

                right?.also {
                    if (it.dfs()) {
                        right = null
                    }
                }

                return `val` == target && left == null && right == null
            }

            head.dfs()

            return head.left
        }
    }

    expect {
        Solution().removeLeafNodes(
            null, 1
        )
    }
}

