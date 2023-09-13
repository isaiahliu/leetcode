package p24xx

import util.TreeNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {
            val minDepths = hashMapOf<Int, Int>()
            val subRootList = LinkedList<Int>()
            var maxDepth = 0
            fun TreeNode.dfs(depth: Int): Int {
                val depth1 = left?.dfs(depth + 1) ?: depth
                val depth2 = right?.dfs(depth + 1) ?: depth

                val max = depth1.coerceAtLeast(depth2)
                val min = depth1.coerceAtMost(depth2)

                if (max > maxDepth) {
                    subRootList.clear()

                    maxDepth = max
                }

                if (max == maxDepth) {
                    subRootList.push(`val`)
                }

                minDepths[`val`] = min

                return max
            }

            root?.dfs(0)

            val subRootMap = hashMapOf<Int, Int>()
            var min = minDepths[subRootList.poll()] ?: 0

            while (subRootList.isNotEmpty()) {
                val next = subRootList.poll()

                subRootMap[next] = min

                minDepths[next]?.also {
                    min = min.coerceAtLeast(it)
                }
            }

            return queries.map { node ->
                subRootMap[node] ?: maxDepth
            }.toIntArray()
        }
    }

    expect {
        Solution().treeQueries(
            TreeNode(
                1,
                TreeNode(
                    4,
                    TreeNode(5, TreeNode(7)),
                    TreeNode(6),
                ),
                TreeNode(3, TreeNode(2)),
            ), intArrayOf(4)
        )
    }
}