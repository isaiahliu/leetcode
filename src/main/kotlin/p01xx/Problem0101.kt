package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            return p == null && q == null || p?.`val` == q?.`val` && isSameTree(
                p?.left, q?.right
            ) && isSameTree(p?.right, q?.left)
        }

        fun isSymmetric(root: TreeNode?): Boolean {
            return isSameTree(root?.left, root?.right)
        }
    }

    expect {
        Solution().isSameTree(
            TreeNode(68), TreeNode(68)
        )
    }
}

