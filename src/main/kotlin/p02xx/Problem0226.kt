package p02xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun invertTree(root: TreeNode?): TreeNode? {
            root ?: return null

            val left = root.left
            val right = root.right

            root.left = invertTree(right)
            root.right = invertTree(left)

            return root
        }
    }

    measureTimeMillis {
        Solution().invertTree(
            null
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

