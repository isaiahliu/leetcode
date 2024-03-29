package p01xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maxDepth(root: TreeNode?): Int {
            var depth = 0

            val queue = LinkedList<TreeNode>()
            root?.also { queue.add(it) }

            while (true) {
                if (queue.isEmpty()) {
                    break
                } else {
                    depth++
                }
                repeat(queue.size) {
                    val top = queue.pop()

                    if (top != null) {
                        top.left?.also { queue.add(it) }
                        top.right?.also { queue.add(it) }
                    }
                }
            }
            return depth
        }
    }

    expect {
        Solution().maxDepth(
            TreeNode(68)
        )
    }
}

