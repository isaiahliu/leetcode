package p13xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun maxSumBST(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(): Pair<Pair<Int, Int>, Int>? {
                var minValue = `val`
                var maxValue = `val`

                val leftResult = left?.dfs()
                val rightResult = right?.dfs()

                var sum = `val`
                left?.also {
                    leftResult?.also { (m, s) ->
                        if (`val` <= m.second) {
                            return null
                        } else {
                            minValue = minValue.coerceAtMost(m.first)
                            sum += s
                        }
                    } ?: return null
                }

                right?.also {
                    rightResult?.also { (m, s) ->
                        if (`val` >= m.first) {
                            return null
                        } else {
                            maxValue = maxValue.coerceAtLeast(m.second)
                            sum += s
                        }
                    } ?: return null
                }

                result = result.coerceAtLeast(sum)

                return (minValue to maxValue to sum)
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().maxSumBST(
            null
        )
    }
}
