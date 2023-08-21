package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun preorderTraversal(root: TreeNode?): List<Int> {
            val result = arrayListOf<Int>()

            fun TreeNode.walk() {
                result.add(`val`)

                left?.walk()
                right?.walk()
            }

            root?.walk()

            return result
        }
    }

    expect {
        Solution().preorderTraversal(
            null
        )
    }
}

