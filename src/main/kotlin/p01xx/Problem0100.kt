package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            return p?.`val` == q?.`val` && isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }

    expect {
        Solution().isSameTree(
            TreeNode(68), TreeNode(68)
        )
    }
}

