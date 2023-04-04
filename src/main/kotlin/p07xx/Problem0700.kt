package p07xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
            root ?: return null

            return if (root.`val` == `val`) {
                root
            } else {
                searchBST(root.left, `val`) ?: searchBST(root.right, `val`)
            }
        }
    }

    measureTimeMillis {
        Solution().searchBST(
            null, 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}