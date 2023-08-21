package p14xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun pseudoPalindromicPaths(root: TreeNode?): Int {
            var status = 0

            var result = 0
            fun TreeNode.dfs() {
                status = status xor (1 shl `val`)

                if (left == null && right == null) {
                    if (Integer.bitCount(status) <= 1) {
                        result++
                    }
                } else {
                    left?.dfs()
                    right?.dfs()
                }

                status = status xor (1 shl `val`)
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().pseudoPalindromicPaths(
            TreeNode(
                2,
                TreeNode(
                    3,
                    TreeNode(3), TreeNode(1)
                ),
                TreeNode(1, null, TreeNode(1))
            )
        )

    }
}

