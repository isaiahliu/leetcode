package p15xx

import util.TreeNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countPairs(root: TreeNode?, distance: Int): Int {
            if (distance == 1) {
                return 0
            }

            var result = 0

            fun TreeNode.dfs(): TreeMap<Int, Int> {
                val counts = TreeMap<Int, Int>()
                if (left == null && right == null) {
                    counts[1] = 1
                } else {
                    val leftMap = left?.dfs()
                    val rightMap = right?.dfs()

                    (1 until distance).forEach { d ->
                        leftMap?.get(d)?.also { leftCount ->
                            rightMap?.headMap(distance - d, true)?.values?.sum()?.also {
                                result += leftCount * it
                            }
                        }
                    }

                    arrayOf(leftMap, rightMap).forEach {
                        it?.forEach { (key, value) ->
                            counts[key + 1] = (counts[key + 1] ?: 0) + value
                        }
                    }
                }

                return counts
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().countPairs(
            TreeNode(
                1,
                TreeNode(2, TreeNode(4), TreeNode(5)),
                TreeNode(3, TreeNode(6), TreeNode(7))
            ), 3
        )
    }
}

