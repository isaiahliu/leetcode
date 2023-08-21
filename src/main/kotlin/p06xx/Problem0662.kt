package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun widthOfBinaryTree(root: TreeNode?): Int {
            val depthRange = hashMapOf<Int, LongArray>()

            fun TreeNode.dfs(depth: Int, startIndex: Long) {
                val range = depthRange.computeIfAbsent(depth) {
                    longArrayOf(startIndex, startIndex)
                }

                range[0] = range[0].coerceAtMost(startIndex)
                range[1] = range[1].coerceAtLeast(startIndex)

                left?.dfs(depth + 1, startIndex * 2)
                right?.dfs(depth + 1, startIndex * 2 + 1)
            }

            root?.dfs(0, 0)

            return depthRange.values.map { (left, right) -> right - left + 1 }.max().toInt()
        }
    }

    expect {
        Solution().widthOfBinaryTree(
            TreeNode(
                1, null, TreeNode(2, null, TreeNode(9))
            )
        )
    }
}