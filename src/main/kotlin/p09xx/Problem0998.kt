package p09xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun insertIntoMaxTree(root: TreeNode?, `val`: Int): TreeNode {
            val newNode = TreeNode(`val`)

            return if (root == null || `val` > root.`val`) {
                newNode.also { it.left = root }
            } else {
                fun TreeNode.dfs() {
                    if (right?.takeIf { it.`val` > `val` } == null) {
                        right = newNode.also { it.left = right }
                    } else {
                        right?.dfs()
                    }
                }

                root.dfs()

                root
            }
        }
    }

    expect {
        Solution().insertIntoMaxTree(
            null, 1
        )
    }
}
