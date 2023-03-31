package p06xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTarget(root: TreeNode?, k: Int): Boolean {
            val set = hashSetOf<Int>()

            var result = false
            fun TreeNode.dfs() {
                if (result) {
                    return
                }

                if (k - `val` in set) {
                    result = true
                } else {
                    set.add(`val`)

                    left?.dfs()
                    right?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().findTarget(
            null, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}