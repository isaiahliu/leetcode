package p04xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun sumOfLeftLeaves(root: TreeNode?): Int {
            fun TreeNode?.sum(fromLeft: Boolean): Int {
                this ?: return 0

                return if (left == null && right == null) {
                    if (fromLeft) `val` else 0
                } else {
                    left.sum(true) + right.sum(false)
                }
            }

            return root.sum(false)
        }
    }

    expect {
        Solution().sumOfLeftLeaves(
            null
        )
    }
}


