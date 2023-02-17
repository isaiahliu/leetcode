package p01xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
            if (root == null) {
                return emptyList()
            }

            val result = arrayListOf<List<Int>>()
            val queue = LinkedList<TreeNode>()
            queue.add(root)

            var direction = false

            while (true) {
                val nums = arrayListOf<Int>()
                repeat(queue.size) {

                    val top = queue.pop()

                    if (top != null) {
                        nums.add(top.`val`)

                        if (direction) {
                            top.right?.also { queue.add(it) }
                            top.left?.also { queue.add(it) }
                        } else {
                            top.left?.also { queue.add(it) }
                            top.right?.also { queue.add(it) }
                        }
                    }
                }

                if (nums.isEmpty()) {
                    break
                } else {
                    result.add(nums)
                }

                queue.reverse()
                direction = !direction
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().zigzagLevelOrder(
            TreeNode(68)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

