package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun averageOfLevels(root: TreeNode?): DoubleArray {
            val result = arrayListOf<MutableList<Int>>()

            fun TreeNode.dfs(depth: Int) {
                if (depth == result.size) {
                    result.add(arrayListOf())
                }

                result[depth].add(`val`)

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(0)

            return result.map { it.average() }.toDoubleArray()
        }
    }

    expect {
        Solution().averageOfLevels(
            null
        )

    }
}