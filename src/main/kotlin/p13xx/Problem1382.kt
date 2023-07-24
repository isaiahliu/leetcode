package p13xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun balanceBST(root: TreeNode?): TreeNode? {
            fun TreeNode.dfs(): MutableList<TreeNode> {
                val result = arrayListOf<TreeNode>()

                left?.dfs()?.also {
                    result.addAll(it)
                }

                result.add(this)

                right?.dfs()?.also {
                    result.addAll(it)
                }

                this.left = null
                this.right = null

                return result
            }

            val nodes = root?.dfs() ?: return null

            fun buildTree(start: Int, end: Int): TreeNode? {
                if (start > end) {
                    return null
                }
                val mid = (start + end) / 2

                val parent = nodes[mid]

                parent.left = buildTree(start, mid - 1)
                parent.right = buildTree(mid + 1, end)

                return parent
            }

            return buildTree(0, nodes.lastIndex)
        }
    }

    measureTimeMillis {
        Solution().balanceBST(null).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

