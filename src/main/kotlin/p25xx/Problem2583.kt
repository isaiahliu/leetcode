package p25xx

import util.TreeNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun kthLargestLevelSum(root: TreeNode?, k: Int): Long {
            val sums = PriorityQueue<Long>()

            val nodes = LinkedList<TreeNode>()

            root?.also { nodes += it }

            while (nodes.isNotEmpty()) {
                var sum = 0L

                repeat(nodes.size) {
                    val node = nodes.poll()

                    sum += node.`val`

                    node.left?.also { nodes += it }
                    node.right?.also { nodes += it }
                }

                sums += sum

                if (sums.size > k) {
                    sums.poll()
                }
            }

            return sums.takeIf { it.size == k }?.peek() ?: -1L
        }
    }

    expect {
        Solution().kthLargestLevelSum(
            null, 6
        )
    }
}