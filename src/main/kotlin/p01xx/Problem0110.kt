package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isBalanced(root: TreeNode?): Boolean {
            fun TreeNode?.balanceDepth(): Int {
                if (this == null) {
                    return 0
                }

                val leftDepth = left.balanceDepth()

                if (leftDepth == -1) {
                    return -1
                }

                val rightDepth = right.balanceDepth()

                if (rightDepth == -1) {
                    return -1
                }

                return if (leftDepth - rightDepth in -1..1) {
                    leftDepth.coerceAtLeast(rightDepth) + 1
                } else {
                    -1
                }
            }

            return root.balanceDepth() > -1
        }
    }

    expect {
        Solution().isBalanced(
            null
        )
    }
}

