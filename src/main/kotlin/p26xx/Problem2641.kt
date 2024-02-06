package p26xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun replaceValueInTree(root: TreeNode?): TreeNode? {
            val depthSums = hashMapOf<Int, Int>()

            fun TreeNode.sum(depth: Int) {
                depthSums[depth] = (depthSums[depth] ?: 0) + `val`

                left?.sum(depth + 1)
                right?.sum(depth + 1)
            }

            root?.sum(0)

            fun TreeNode.writeValue(depth: Int, parentSum: Int) {
                `val` = (depthSums[depth] ?: 0) - parentSum

                val sum = (left?.`val` ?: 0) + (right?.`val` ?: 0)

                left?.writeValue(depth + 1, sum)
                right?.writeValue(depth + 1, sum)
            }

            root?.also {
                it.writeValue(0, it.`val`)
            }

            return root
        }
    }

    expect {
        Solution().replaceValueInTree(
            null
        )
    }
}