package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
            fun build(inRange: IntRange, postRange: IntRange): TreeNode? {
                if (inRange.first > inRange.last) {
                    return null
                }

                val root = TreeNode(postorder[postRange.last])

                var rootInOrderIndex = postRange.first
                while (inorder[rootInOrderIndex] != root.`val`) {
                    rootInOrderIndex++
                }

                root.left = build(
                    inRange.first until rootInOrderIndex,
                    postRange.first until postRange.first + rootInOrderIndex - inRange.first
                )

                root.right = build(
                    rootInOrderIndex + 1..inRange.last,
                    postRange.first + rootInOrderIndex - inRange.first until postRange.last
                )

                return root
            }

            return build(inorder.indices, postorder.indices)
        }
    }

    measureTimeMillis {
        Solution().buildTree(
            intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

