package p09xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
            var result = 0

            fun TreeNode.dfs() {
                if (`val` > high) {
                    left?.dfs()
                } else if (`val` < low) {
                    right?.dfs()
                } else {
                    result += `val`

                    left?.dfs()
                    right?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().rangeSumBST(
            null, 1, 1
        )
    }
}