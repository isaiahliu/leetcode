package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun postorderTraversal(root: TreeNode?): List<Int> {
            val result = arrayListOf<Int>()

            fun TreeNode.walk() {
                left?.walk()
                right?.walk()
                result.add(`val`)
            }

            root?.walk()

            return result
        }
    }

    measureTimeMillis {
        Solution().postorderTraversal(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

