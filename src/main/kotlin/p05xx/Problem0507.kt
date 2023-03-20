package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findFrequentTreeSum(root: TreeNode?): IntArray {
            val resultMap = hashMapOf<Int, Int>()

            fun TreeNode.dfs(): Int {
                var sum = `val`

                left?.dfs()?.also { sum += it }
                right?.dfs()?.also { sum += it }

                resultMap[sum] = (resultMap[sum] ?: 0) + 1

                return sum
            }

            root?.dfs()

            val max = resultMap.values.max()

            return resultMap.filterValues { it == max }.keys.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().findFrequentTreeSum(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}