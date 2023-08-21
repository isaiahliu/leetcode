package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
            root ?: return null

            return when {
                root.`val` < low -> trimBST(root.right, low, high)
                root.`val` > high -> trimBST(root.left, low, high)
                else -> {
                    root.left = trimBST(root.left, low, high)
                    root.right = trimBST(root.right, low, high)

                    root
                }
            }
        }
    }

    expect {
        Solution().trimBST(
            null, 3, 6
        )
    }
}