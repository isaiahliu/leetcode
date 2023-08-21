package p00xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isValidBST(root: TreeNode?): Boolean {
            fun TreeNode?.isValid(lowerLimit: Int? = null, upperLimit: Int? = null): Boolean {
                if (this == null) {
                    return true
                }

                if (lowerLimit != null && `val` <= lowerLimit) {
                    return false
                }

                if (upperLimit != null && `val` >= upperLimit) {
                    return false
                }

                return left.isValid(lowerLimit, `val`) && right.isValid(`val`, upperLimit)
            }

            return root.isValid()
        }
    }

    expect {
        Solution().isValidBST(
            TreeNode(5, TreeNode(4, TreeNode(6)))
        )
    }
}

