package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sumNumbers(root: TreeNode?): Int {
            var result = 0
            fun TreeNode.walk(base: Int = 0) {
                val num = base + `val`

                if (left == null && right == null) {
                    result += num
                } else {
                    left?.walk(num * 10)
                    right?.walk(num * 10)
                }
            }

            root?.walk()

            return result
        }
    }

    measureTimeMillis {
        Solution().sumNumbers(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

