package p01xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minDepth(root: TreeNode?): Int {
            val queue = LinkedList<TreeNode>()

            root?.also { queue.add(it) }

            var depth = 0
            found@ while (queue.isNotEmpty()) {
                depth++
                for (i in queue.indices) {
                    val top = queue.pop()

                    if (top.left == null && top.right == null) {
                        break@found
                    }

                    top.left?.also { queue.add(it) }
                    top.right?.also { queue.add(it) }
                }
            }

            return depth
        }
    }

    expect {
        Solution().minDepth(
            null
        )
    }
}

