package p13xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun deepestLeavesSum(root: TreeNode?): Int {
            var result = 0
            var maxDepth = -1
            fun TreeNode.dfs(depth: Int) {
                if (depth > maxDepth) {
                    maxDepth = depth
                    result = 0
                }

                if (depth == maxDepth) {
                    result += `val`
                }

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(0)

            return result
        }
    }

    expect {
        Solution().deepestLeavesSum(
            null
        )
    }
}

