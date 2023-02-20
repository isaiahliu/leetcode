package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxPathSum(root: TreeNode?): Int {
            var result = root?.`val` ?: 0

            fun TreeNode.walk(): Int {
                val leftValue = left?.walk()?.takeIf { it > 0 } ?: 0
                val rightValue = right?.walk()?.takeIf { it > 0 } ?: 0

                result = result.coerceAtLeast(leftValue + rightValue + `val`)

                return `val` + leftValue.coerceAtLeast(rightValue)
            }

            root?.walk()

            return result
        }
    }

    measureTimeMillis {
        Solution().maxPathSum(null).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

