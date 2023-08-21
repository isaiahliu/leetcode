package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun postorderTraversal(root: TreeNode?): List<Int> {
            val result = arrayListOf<Int>()

            fun TreeNode.walk() {
                left?.walk()
                right?.walk()
                result.add(`val`)
            }

            root?.walk()

            return result
        }
    }

    expect {
        Solution().postorderTraversal(
            null
        )
    }
}

