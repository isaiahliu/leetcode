package p09xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isUnivalTree(root: TreeNode?): Boolean {
            return when {
                root == null -> true
                root.left != null && root.left?.`val` != root.`val` -> false
                root.right != null && root.right?.`val` != root.`val` -> false
                else -> isUnivalTree(root.left) && isUnivalTree(root.right)
            }
        }
    }

    expect {
        Solution().isUnivalTree(
            null
        )
    }
}
