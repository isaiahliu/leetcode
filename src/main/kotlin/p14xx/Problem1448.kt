package p14xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun goodNodes(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(max: Int) {
                if (`val` >= max) {
                    result++
                }

                max.coerceAtLeast(`val`).also {
                    left?.dfs(it)
                    right?.dfs(it)
                }
            }

            root?.dfs(Int.MIN_VALUE)

            return result
        }
    }

    measureTimeMillis {
        Solution().goodNodes(
            null
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

