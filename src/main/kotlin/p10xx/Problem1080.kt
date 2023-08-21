package p10xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun sufficientSubset(root: TreeNode?, limit: Int): TreeNode? {
            fun TreeNode.dfs(parentSum: Int): Int {
                val sum = parentSum + `val`

                var maxChildSum: Int? = null

                left?.dfs(sum)?.also {
                    maxChildSum = it

                    if (sum + it < limit) {
                        left = null
                    }
                }

                right?.dfs(sum)?.also {
                    maxChildSum = maxChildSum?.coerceAtLeast(it) ?: it

                    if (sum + it < limit) {
                        right = null
                    }
                }

                return `val` + (maxChildSum ?: 0)
            }

            return root?.takeIf { it.dfs(0) >= limit }
        }
    }

    expect {
        Solution().sufficientSubset(
            null, 22
        )
    }
}
