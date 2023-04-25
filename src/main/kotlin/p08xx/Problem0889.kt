package p08xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun constructFromPrePost(preorder: IntArray, postorder: IntArray): TreeNode? {
            val stack = LinkedList<TreeNode>()

            stack.add(TreeNode(Int.MAX_VALUE))

            var postIndex = 0

            preorder.forEach { num ->
                val node = TreeNode(num)

                stack.peek()?.also {
                    if (it.left == null) {
                        it.left = node
                    } else {
                        it.right = node
                    }
                }

                stack.push(node)

                while (postIndex < postorder.size && postorder[postIndex] == stack.peek().`val`) {
                    stack.poll()
                    postIndex++
                }
            }

            return stack.peek().left
        }
    }

    measureTimeMillis {
        Solution().constructFromPrePost(
            intArrayOf(1, 2, 5), intArrayOf(2, 4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}