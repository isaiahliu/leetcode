package p22xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun checkTree(root: TreeNode?): Boolean {
            var result = 0
            root?.`val`?.also { result += it }
            root?.left?.`val`?.also { result -= it }
            root?.right?.`val`?.also { result -= it }

            return result == 0
        }
    }

    expect {
        Solution().checkTree(
            null
        )
    }
}