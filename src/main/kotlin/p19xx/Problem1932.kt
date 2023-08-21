package p19xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun canMerge(trees: List<TreeNode>): TreeNode? {
            val singles = hashSetOf<Int>()

            val roots = hashMapOf<Int, TreeNode>()
            val leaves = hashSetOf<Int>()

            fun TreeNode.dfs(): Boolean {
                if (left == null && right == null) {
                    if (`val` in leaves) {
                        return false
                    }
                    leaves.add(`val`)
                } else {
                    left?.also {
                        if (!it.dfs()) {
                            return false
                        }
                    }

                    right?.also {
                        if (!it.dfs()) {
                            return false
                        }
                    }
                }

                return true
            }

            trees.forEach {
                when {
                    it.left == null && it.right == null -> {
                        singles.add(it.`val`)
                    }

                    it.`val` in roots -> {
                        return null
                    }

                    !it.dfs() -> {
                        return null
                    }

                    else -> {
                        roots[it.`val`] = it
                    }
                }
            }

            if (roots.isEmpty()) {
                return trees.firstOrNull()?.takeIf { singles.size == 1 }
            }

            singles.removeAll(roots.keys)
            singles.removeAll(leaves)

            if (singles.isNotEmpty()) {
                return null
            }

            val singleRoot = roots.keys - leaves
            if (singleRoot.size != 1) {
                return null
            }

            val root = roots.remove(singleRoot.first()) ?: return null

            var failed = false
            fun TreeNode.mergeAndValidate(min: Int, max: Int) {
                if (failed) {
                    return
                }

                if (`val` < min || `val` > max) {
                    failed = true
                    return
                }

                if (left == null && right == null) {
                    roots.remove(`val`)?.also {
                        left = it.left
                        right = it.right
                    }
                }

                left?.mergeAndValidate(min, `val` - 1)
                right?.mergeAndValidate(`val` + 1, max)
            }

            return root.takeIf {
                root.mergeAndValidate(Int.MIN_VALUE, Int.MAX_VALUE)
                !failed && roots.isEmpty()
            }
        }
    }

    expect {
        Solution().canMerge(
            listOf(
                TreeNode(2, TreeNode(1), TreeNode(3)),
                TreeNode(1),
                TreeNode(3),
            )
        )
    }
}