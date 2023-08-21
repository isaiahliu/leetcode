package p02xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun invertTree(root: TreeNode?): TreeNode? {
            root ?: return null

            val left = root.left
            val right = root.right

            root.left = invertTree(right)
            root.right = invertTree(left)

            return root
        }
    }

    expect {
        Solution().invertTree(
            null
        )
    }
}

