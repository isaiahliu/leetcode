package p01xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun levelOrder(root: TreeNode?): List<List<Int>> {
            if (root == null) {
                return emptyList()
            }

            val result = arrayListOf<List<Int>>()
            val queue = LinkedList<TreeNode>()
            queue.add(root)

            while (true) {
                val nums = arrayListOf<Int>()
                repeat(queue.size) {
                    val top = queue.pop()

                    if (top != null) {
                        nums.add(top.`val`)

                        top.left?.also { queue.add(it) }
                        top.right?.also { queue.add(it) }
                    }
                }

                if (nums.isEmpty()) {
                    break
                } else {
                    result.add(nums)
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().levelOrder(
            TreeNode(68)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

