package p13xx

import util.TreeNode
import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxProduct(root: TreeNode?): Int {
            var sum = 0L
            fun TreeNode.dfsSum() {
                sum += `val`
                left?.dfsSum()
                right?.dfsSum()
            }

            root?.dfsSum()

            var minDiff = sum

            fun TreeNode.dfsDiff(): Long {
                var subSum = `val`.toLong()

                left?.dfsDiff()?.also { subSum += it }
                right?.dfsDiff()?.also { subSum += it }

                minDiff = minDiff.coerceAtMost((subSum * 2 - sum).absoluteValue)

                return subSum
            }

            root?.dfsDiff()

            return (((sum - minDiff) / 2).let { it * (it + minDiff) } % 1000000007).toInt()
        }
    }

    measureTimeMillis {
        Solution().maxProduct(
            TreeNode(
                1,
                TreeNode(
                    2,
                    TreeNode(4),
                    TreeNode(5),
                ),
                TreeNode(
                    3,
                    TreeNode(6),
                )
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

