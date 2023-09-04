package p22xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun averageOfSubtree(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(): Pair<Int, Int> {
                var count = 1
                var sum = `val`

                left?.dfs()?.also { (c, s) ->
                    count += c
                    sum += s
                }

                right?.dfs()?.also { (c, s) ->
                    count += c
                    sum += s
                }

                if (sum / count == `val`) {
                    result++
                }

                return count to sum
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().averageOfSubtree(
            null
        )
    }
}