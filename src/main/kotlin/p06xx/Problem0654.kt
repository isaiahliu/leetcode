package p06xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
            val root = TreeNode(Int.MAX_VALUE)

            val stack = LinkedList<TreeNode>()
            stack.push(root)

            nums.forEach {
                val node = TreeNode(it)

                var left: TreeNode? = null
                while (it > stack.peek().`val`) {
                    left = stack.poll()
                }

                node.left = left

                stack.peek().right = node
                stack.push(node)
            }

            return root.right
        }
    }

    expect {
        Solution().constructMaximumBinaryTree(
            intArrayOf()
        )
    }
}