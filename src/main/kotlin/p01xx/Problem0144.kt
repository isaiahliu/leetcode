package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun preorderTraversal(root: TreeNode?): List<Int> {
            val result = arrayListOf<Int>()

            fun TreeNode.walk() {
                result.add(`val`)

                left?.walk()
                right?.walk()
            }

            root?.walk()

            return result
        }
    }

    measureTimeMillis {
        Solution().preorderTraversal(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

