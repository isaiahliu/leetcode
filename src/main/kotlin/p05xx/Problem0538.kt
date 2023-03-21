package p05xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun convertBST(root: TreeNode?): TreeNode? {
            var sum = 0

            fun TreeNode.dfs() {
                right?.dfs()

                sum += `val`
                `val` = sum

                left?.dfs()
            }

            root?.dfs()

            return root
        }
    }

    measureTimeMillis {
        Solution().convertBST(null).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}