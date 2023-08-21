package p08xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun pruneTree(root: TreeNode?): TreeNode? {
            fun TreeNode.dfs(): Int {
                var result = `val`

                left?.dfs()?.also {
                    if (it == 0) {
                        left = null
                    } else {
                        result = 1
                    }
                }

                right?.dfs()?.also {
                    if (it == 0) {
                        right = null
                    } else {
                        result = 1
                    }
                }

                return result
            }

            return root?.dfs()?.takeIf { it > 0 }?.let { root }
        }
    }

    expect {
        Solution().pruneTree(
            null
        )
    }
}