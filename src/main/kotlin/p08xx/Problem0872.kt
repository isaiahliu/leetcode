package p08xx

import util.TreeNode
import java.util.*
import util.expect

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

    expect {
        Solution().leafSimilar(
            null, null
        )

    }
}