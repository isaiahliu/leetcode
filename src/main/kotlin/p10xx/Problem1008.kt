package p10xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun bstFromPreorder(preorder: IntArray): TreeNode {
            fun dfs(fromIndex: Int, toIndex: Int): TreeNode {
                val root = TreeNode(preorder[fromIndex])

                var leftEndIndex = fromIndex + 1
                while (leftEndIndex < preorder.size && preorder[leftEndIndex] < root.`val`) {
                    leftEndIndex++
                }

                leftEndIndex--

                if (leftEndIndex > fromIndex) {
                    root.left = dfs(fromIndex + 1, leftEndIndex)
                }

                if (toIndex > leftEndIndex) {
                    root.right = dfs(leftEndIndex + 1, toIndex)
                }

                return root
            }

            return dfs(0, preorder.lastIndex)
        }
    }

    expect {
        Solution().bstFromPreorder(
            intArrayOf(8, 5, 1, 7, 10, 12)
        )
    }
}
