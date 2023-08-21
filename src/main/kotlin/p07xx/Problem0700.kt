package p07xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
            root ?: return null

            return if (root.`val` == `val`) {
                root
            } else {
                searchBST(root.left, `val`) ?: searchBST(root.right, `val`)
            }
        }
    }

    expect {
        Solution().searchBST(
            null, 1
        )
    }
}