package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun smallestFromLeaf(root: TreeNode?): String {
            val parentMap = hashMapOf<TreeNode, TreeNode>()

            var min = 26
            val leaf = hashSetOf<TreeNode>()
            fun TreeNode.dfs() {
                left?.also {
                    parentMap[it] = this
                    it.dfs()
                }

                right?.also {
                    parentMap[it] = this
                    it.dfs()
                }

                if (left == null && right == null) {
                    if (`val` < min) {
                        min = `val`
                        leaf.clear()
                    }

                    if (`val` == min) {
                        leaf.add(this)
                    }
                }
            }

            root?.dfs()

            val result = StringBuilder("${'a' + min}")

            while (true) {
                min = 26
                leaf.toSet().also { leaf.clear() }.forEach {
                    parentMap[it]?.also {
                        if (it.`val` < min) {
                            min = it.`val`
                            leaf.clear()
                        }

                        if (it.`val` == min) {
                            leaf.add(it)
                        }
                    } ?: return result.toString()
                }

                result.append('a' + min)
            }
        }
    }

    measureTimeMillis {
        Solution().smallestFromLeaf(
            TreeNode(
                4, TreeNode(0, TreeNode(1)),
                TreeNode(1)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
