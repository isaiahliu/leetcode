package p04xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun pathSum(root: TreeNode?, targetSum: Int): Int {
            var result = 0

            fun TreeNode.dfs(route: List<Long>) {
                val newRoute = route.map { it + `val` } + `val`.toLong()

                result += newRoute.count { it == targetSum.toLong() }

                left?.dfs(newRoute)
                right?.dfs(newRoute)
            }

            root?.dfs(emptyList())

            return result
        }
    }

    expect {
        Solution().pathSum(
            null, 1
        )
    }
}


