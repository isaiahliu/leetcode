package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
            var result = 0

            fun TreeNode.dfs() {
                if (`val` > high) {
                    left?.dfs()
                } else if (`val` < low) {
                    right?.dfs()
                } else {
                    result += `val`

                    left?.dfs()
                    right?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().rangeSumBST(
            null, 1, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}