package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun findSecondMinimumValue(root: TreeNode?): Int {
            var result: Int? = null
            val min = root?.`val` ?: -1

            fun TreeNode.dfs() {
                when {
                    result?.takeIf { `val` >= it } != null -> return
                    `val` == min -> {
                        left?.dfs()
                        right?.dfs()
                    }

                    else -> result = `val`.coerceAtMost(result ?: `val`)
                }
            }

            root?.dfs()

            return result ?: -1
        }
    }

    expect {
        Solution().findSecondMinimumValue(
            null
        )
    }
}