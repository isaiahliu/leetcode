package p09xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isCompleteTree(root: TreeNode?): Boolean {
            val bfs = LinkedList<TreeNode>()

            root?.also { bfs.add(it) }

            var lastLevel = bfs.isEmpty()
            while (!lastLevel) {
                repeat(bfs.size) {
                    val n = bfs.pop()

                    val left = n.left
                    val right = n.right

                    if (left != null) {
                        if (lastLevel) {
                            return false
                        } else {
                            bfs.add(left)
                        }
                    } else {
                        lastLevel = true
                    }

                    if (right != null) {
                        if (lastLevel) {
                            return false
                        } else {
                            bfs.add(right)
                        }
                    } else {
                        lastLevel = true
                    }
                }
            }

            return bfs.all { it.left == null && it.right == null }
        }
    }

    expect {
        Solution().isCompleteTree(
            null
        )
    }
}
