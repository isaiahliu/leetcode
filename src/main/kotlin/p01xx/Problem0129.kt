package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun sumNumbers(root: TreeNode?): Int {
            var result = 0
            fun TreeNode.walk(base: Int = 0) {
                val num = base + `val`

                if (left == null && right == null) {
                    result += num
                } else {
                    left?.walk(num * 10)
                    right?.walk(num * 10)
                }
            }

            root?.walk()

            return result
        }
    }

    expect {
        Solution().sumNumbers(
            null
        )
    }
}

