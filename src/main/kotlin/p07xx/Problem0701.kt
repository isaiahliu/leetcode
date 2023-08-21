package p07xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode {
            root ?: return TreeNode(`val`)

            if (root.`val` < `val`) {
                if (root.right == null) {
                    root.right = TreeNode(`val`)
                } else {
                    insertIntoBST(root.right, `val`)
                }
            } else {
                if (root.left == null) {
                    root.left = TreeNode(`val`)
                } else {
                    insertIntoBST(root.left, `val`)
                }
            }

            return root
        }
    }

    expect {
        Solution().insertIntoBST(
            null, 1
        )
    }
}