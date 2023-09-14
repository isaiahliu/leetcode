package p24xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun minimumOperations(root: TreeNode?): Int {
            var nodes = arrayListOf<TreeNode>()
            root?.also { nodes.add(it) }

            var result = 0

            while (nodes.isNotEmpty()) {
                val sortedIndices = nodes.indices.sortedBy { nodes[it].`val` }
                val visited = hashSetOf<Int>()

                sortedIndices.forEach {
                    if (visited.add(it)) {
                        var t = it
                        while (true) {
                            t = sortedIndices[t]

                            if (visited.add(t)) {
                                result++
                            } else {
                                break
                            }
                        }
                    }
                }

                val newNodes = arrayListOf<TreeNode>()
                nodes.forEach {
                    it.left?.also { newNodes += it }
                    it.right?.also { newNodes += it }
                }

                nodes = newNodes
            }

            return result
        }
    }

    expect {
        Solution().minimumOperations(
            TreeNode(
                1, TreeNode(
                    4,
                    TreeNode(7), TreeNode(6)
                ), TreeNode(
                    3,
                    TreeNode(8), TreeNode(5)
                )
            )
        )
    }
}