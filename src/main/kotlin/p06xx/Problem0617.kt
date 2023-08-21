package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
            root1 ?: return root2
            root2 ?: return root1

            root1.`val` += root2.`val`
            root1.left = mergeTrees(root1.left, root2.left)
            root1.right = mergeTrees(root1.right, root2.right)

            return root1
        }
    }

    expect {
        Solution().mergeTrees(
            null, null
        )

    }
}