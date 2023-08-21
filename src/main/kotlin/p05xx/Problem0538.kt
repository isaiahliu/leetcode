package p05xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun convertBST(root: TreeNode?): TreeNode? {
            var sum = 0

            fun TreeNode.dfs() {
                right?.dfs()

                sum += `val`
                `val` = sum

                left?.dfs()
            }

            root?.dfs()

            return root
        }
    }

    expect {
        Solution().convertBST(null)
    }
}