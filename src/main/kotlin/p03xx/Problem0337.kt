package p03xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rob(root: TreeNode?): Int {
            class Dp(val node: TreeNode) {
                val left: Dp? = node.left?.let { Dp(it) }
                val right: Dp? = node.right?.let { Dp(it) }

                val max: IntArray
                    get() {
                        val leftMax = left?.max
                        val rightMax = right?.max

                        val leftSteal = leftMax?.get(0) ?: 0
                        val leftSkip = leftMax?.get(1) ?: 0

                        val rightSteal = rightMax?.get(0) ?: 0
                        val rightSkip = rightMax?.get(1) ?: 0

                        return intArrayOf(
                            node.`val` + leftSkip + rightSkip,
                            leftSteal.coerceAtLeast(leftSkip) + rightSteal.coerceAtLeast(rightSkip)
                        )
                    }
            }

            return root?.let { Dp(it).max }?.let { it[0].coerceAtLeast(it[1]) } ?: 0
        }
    }

    measureTimeMillis {
        Solution().rob(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

