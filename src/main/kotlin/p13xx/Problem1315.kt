package p13xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumEvenGrandparent(root: TreeNode?): Int {
            var result = 0
            fun TreeNode.dfs(status: Int) {
                if (status and 0b10 > 0) {
                    result += `val`
                }

                var newStatus = (status shl 1) and 0b11
                if (`val` % 2 == 0) {
                    newStatus++
                }

                left?.dfs(newStatus)
                right?.dfs(newStatus)
            }

            root?.dfs(0)

            return result
        }
    }

    measureTimeMillis {
        Solution().sumEvenGrandparent(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

