package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
            fun build(preRange: IntRange, inRange: IntRange): TreeNode? {
                if (preRange.first > preRange.last) {
                    return null
                }

                val root = TreeNode(preorder[preRange.first])

                var rootInOrderIndex = inRange.first
                while (inorder[rootInOrderIndex] != root.`val`) {
                    rootInOrderIndex++
                }

                root.left = build(
                    preRange.first + 1..preRange.first + rootInOrderIndex - inRange.first,
                    inRange.first until rootInOrderIndex
                )

                root.right = build(
                    preRange.first + rootInOrderIndex - inRange.first + 1..preRange.last,
                    rootInOrderIndex + 1..inRange.last
                )

                return root
            }

            return build(preorder.indices, inorder.indices)
        }
    }

    expect {
        Solution().buildTree(
            intArrayOf(1), intArrayOf(1)
        )
    }
}

