package p24xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun closestNodes(root: TreeNode?, queries: List<Int>): List<List<Int>> {
            val sorted = queries.indices.sortedBy { queries[it] }
            var i = 0

            val result = List(queries.size) { arrayListOf(-1, -1) }
            var pre = -1
            fun TreeNode.dfs() {
                left?.dfs()

                while (true) {
                    sorted.getOrNull(i)?.takeIf { queries[it] <= `val` }?.also {
                        result[it][1] = `val`

                        result[it][0] = if (queries[it] == `val`) {
                            `val`
                        } else {
                            pre
                        }

                        i++
                    } ?: break
                }
                pre = `val`

                right?.dfs()
            }

            root?.dfs()

            while (i < sorted.size) {
                result[sorted[i++]][0] = pre
            }

            return result
        }
    }

    expect {
        Solution().closestNodes(
            TreeNode(
                16,
                TreeNode(
                    14,
                    TreeNode(4, TreeNode(1)),
                    TreeNode(15)
                )
            ), listOf(10, 6, 2, 9)
        )
    }
}
