package p09xx

import util.TreeNode
import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun distributeCoins(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(): Int {
                var balance = `val` - 1

                left?.dfs()?.also {
                    result += it.absoluteValue
                    balance += it
                }

                right?.dfs()?.also {
                    result += it.absoluteValue
                    balance += it
                }

                return balance
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().distributeCoins(
            null
        )
    }
}
