package p08xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pruneTree(root: TreeNode?): TreeNode? {
            fun TreeNode.dfs(): Int {
                var result = `val`

                left?.dfs()?.also {
                    if (it == 0) {
                        left = null
                    } else {
                        result = 1
                    }
                }

                right?.dfs()?.also {
                    if (it == 0) {
                        right = null
                    } else {
                        result = 1
                    }
                }

                return result
            }

            return root?.dfs()?.takeIf { it > 0 }?.let { root }
        }
    }

    measureTimeMillis {
        Solution().pruneTree(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}