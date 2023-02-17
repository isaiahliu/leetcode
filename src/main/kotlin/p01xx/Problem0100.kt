package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
            return p?.`val` == q?.`val` && isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }

    measureTimeMillis {
        Solution().isSameTree(
            TreeNode(68), TreeNode(68)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

