package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flipMatchVoyage(root: TreeNode?, voyage: IntArray): List<Int> {
            var index = 0

            val result = arrayListOf<Int>()
            fun TreeNode?.dfs(): Boolean {
                if (this == null) {
                    return true
                }
                if (this.`val` == voyage[index]) {
                    index++
                } else {
                    return false
                }

                if (left != null && right?.`val` == voyage[index]) {
                    result.add(`val`)

                    val t = left
                    left = right
                    right = t
                }

                return left.dfs() && right.dfs()
            }

            return if (root.dfs() && index == voyage.size) {
                result
            } else {
                listOf(-1)
            }
        }
    }

    measureTimeMillis {
        Solution().flipMatchVoyage(
            TreeNode(1, null, TreeNode(2)), intArrayOf(1, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
