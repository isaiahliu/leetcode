package p24xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun closestNodes(root: TreeNode?, queries: List<Int>): List<List<Int>> {
            fun TreeNode.ceiling(num: Int): Int? {
                return if (`val` > num) {
                    left?.ceiling(num) ?: `val`
                } else if (`val` < num) {
                    right?.ceiling(num)
                } else {
                    num
                }
            }

            fun TreeNode.floor(num: Int): Int? {
                return if (`val` > num) {
                    left?.floor(num)
                } else if (`val` < num) {
                    right?.floor(num) ?: `val`
                } else {
                    num
                }
            }

            return queries.map {
                listOf(root?.floor(it) ?: -1, root?.ceiling(it) ?: -1)
            }
        }
    }

    expect {
        Solution().closestNodes(
            null, listOf()
        )
    }
}
