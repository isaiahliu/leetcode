package p10xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxAncestorDiff(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(min: Int, max: Int) {
                val newMin = min.coerceAtMost(`val`)
                val newMax = max.coerceAtLeast(`val`)

                result = result.coerceAtLeast(newMax - newMin)

                left?.dfs(newMin, newMax)
                right?.dfs(newMin, newMax)
            }

            root?.dfs(root.`val`, root.`val`)

            return result
        }
    }

    measureTimeMillis {
        Solution().maxAncestorDiff(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}