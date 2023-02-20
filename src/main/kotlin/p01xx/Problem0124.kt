package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxPathSum(root: TreeNode?): Int {
            var result = root?.`val` ?: 0

            fun TreeNode.walk(): Int {
                var leftValue = 0
                var rightValue = 0

                left?.walk()?.also {
                    leftValue = it.coerceAtLeast(0)
                }

                right?.walk()?.also {
                    rightValue = it.coerceAtLeast(0)
                }

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

