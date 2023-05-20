package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isUnivalTree(root: TreeNode?): Boolean {
            return when {
                root == null -> true
                root.left != null && root.left?.`val` != root.`val` -> false
                root.right != null && root.right?.`val` != root.`val` -> false
                else -> isUnivalTree(root.left) && isUnivalTree(root.right)
            }
        }
    }

    measureTimeMillis {
        Solution().isUnivalTree(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
