package p11xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {
            var maxDepth = -1
            var count = 0

            fun TreeNode.dfs(depth: Int) {
                if (depth > maxDepth) {
                    maxDepth = depth
                    count = 1
                } else if (depth == maxDepth) {
                    count++
                }

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(0)

            var result: TreeNode? = root
            var resultDepth = 0
            fun TreeNode.dfs2(depth: Int): Int {
                var d = 0

                if (depth == maxDepth) {
                    d++
                }

                left?.dfs2(depth + 1)?.also { d += it }
                right?.dfs2(depth + 1)?.also { d += it }

                if (d == count && depth > resultDepth) {
                    result = this
                    resultDepth = depth
                }

                return d
            }

            root?.dfs2(0)

            return result
        }
    }

    measureTimeMillis {
        Solution().lcaDeepestLeaves(
            null
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}