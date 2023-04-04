package p07xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode {
            root ?: return TreeNode(`val`)

            if (root.`val` < `val`) {
                if (root.right == null) {
                    root.right = TreeNode(`val`)
                } else {
                    insertIntoBST(root.right, `val`)
                }
            } else {
                if (root.left == null) {
                    root.left = TreeNode(`val`)
                } else {
                    insertIntoBST(root.left, `val`)
                }
            }

            return root
        }
    }

    measureTimeMillis {
        Solution().insertIntoBST(
            null, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}