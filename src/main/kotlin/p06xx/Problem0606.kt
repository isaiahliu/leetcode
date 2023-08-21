package p06xx

import util.TreeNode
import util.expect

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

    expect {
        Solution().tree2str(
            null
        )

    }
}