package p05xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun findMode(root: TreeNode?): IntArray {
            var max = 0

            val result = hashMapOf<Int, Int>()
            fun append(num: Int) {
                ((result[num] ?: 0) + 1).also {
                    result[num] = it
                    max = max.coerceAtLeast(it)
                }
            }

            fun TreeNode.dfs() {
                append(`val`)

                left?.dfs()
                right?.dfs()
            }

            root?.dfs()

            return result.filterValues { it == max }.keys.toIntArray()
        }
    }

    expect {
        Solution().findMode(
            null
        ).toList()
    }
}