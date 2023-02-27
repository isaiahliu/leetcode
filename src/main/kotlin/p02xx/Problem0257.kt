package p02xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun binaryTreePaths(root: TreeNode?): List<String> {
            val result = arrayListOf<String>()
            fun TreeNode.walk(route: List<Int>) {
                val newRoute = route + `val`

                if (left == null && right == null) {
                    result.add(newRoute.joinToString("->"))
                } else {
                    left?.walk(newRoute)
                    right?.walk(newRoute)
                }
            }

            root?.walk(emptyList())

            return result
        }
    }

    measureTimeMillis {
        Solution().binaryTreePaths(
            null
        ).also { println(it) }
    }
}

