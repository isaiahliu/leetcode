package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findBottomLeftValue(root: TreeNode?): Int {
            var maxDepth = -1
            var result = 0

            fun TreeNode.dfs(depth: Int) {
                if (depth > maxDepth) {
                    maxDepth = depth
                    result = `val`
                }

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(0)

            return result
        }
    }

    measureTimeMillis {
        Solution().findBottomLeftValue(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}