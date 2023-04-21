package p08xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
            val leaves = LinkedList<Int>()
            val errorLeaves = LinkedList<Int>()

            fun TreeNode.dfs() {
                if (left == null && right == null) {
                    if (leaves.peekFirst() == `val`) {
                        leaves.pollFirst()
                    } else {
                        errorLeaves.add(`val`)
                    }
                } else {
                    left?.dfs()
                    right?.dfs()
                }
            }

            root1?.dfs()

            leaves.addAll(errorLeaves)
            errorLeaves.clear()

            root2?.dfs()

            return leaves.isEmpty() && errorLeaves.isEmpty()
        }
    }

    measureTimeMillis {
        Solution().leafSimilar(
            null, null
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}