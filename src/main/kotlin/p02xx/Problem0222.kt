package p02xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun countNodes(root: TreeNode?): Int {
            fun TreeNode?.countDepth(index: Int, max: Int): Int {
                if (this == null) {
                    return 0
                }

                return if (max == 0 || index < max / 2) {
                    left.countDepth(index, max / 2)
                } else {
                    right.countDepth(index - max / 2, max / 2)
                } + 1
            }

            val maxDepth = root.countDepth(0, 0)

            if (maxDepth <= 1) {
                return maxDepth
            }

            val maxLeafCount = 1 shl (maxDepth - 1)

            var minIndex = maxLeafCount
            fun binarySearch(startIndex: Int, endIndex: Int) {
                if (startIndex > endIndex) {
                    return
                }

                val midIndex = endIndex - startIndex
                val midDepth = root.countDepth(midIndex, maxLeafCount)

                if (midDepth < maxDepth) {
                    minIndex = midIndex

                    binarySearch(startIndex, midIndex - 1)
                } else {
                    binarySearch(midIndex + 1, endIndex)
                }
            }

            binarySearch(0, maxLeafCount)

            return minIndex + (1 shl (maxDepth - 1)) - 1
        }
    }

    expect {
        Solution().countNodes(
            TreeNode(
                1, TreeNode(
                    2,
                    TreeNode(4),
                    TreeNode(5),
                ), TreeNode(
                    3,
                    TreeNode(6),
                )
            )
        )
    }
}

