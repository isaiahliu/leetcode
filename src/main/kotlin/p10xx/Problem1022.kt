package p10xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumRootToLeaf(root: TreeNode?): Int {
            var result = 0

            fun TreeNode.dfs(sum: Int) {
                val s = sum * 2 + `val`

                if (left == null && right == null) {
                    result += s
                } else {
                    left?.dfs(s)
                    right?.dfs(s)
                }
            }

            root?.dfs(0)

            return result
        }
    }

    measureTimeMillis {
        Solution().sumRootToLeaf(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}