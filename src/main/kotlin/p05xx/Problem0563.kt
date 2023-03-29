package p05xx

import util.TreeNode
import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTilt(root: TreeNode?): Int {
            var result = 0
            fun TreeNode?.sum(): Int {
                this ?: return 0

                val leftSum = left.sum()
                val rightSum = right.sum()

                result += (leftSum - rightSum).absoluteValue

                return `val` + leftSum + rightSum
            }

            root?.sum()

            return result
        }
    }

    measureTimeMillis {
        Solution().findTilt(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}