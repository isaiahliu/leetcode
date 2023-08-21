package p05xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun preorder(root: Node?): List<Int> {
            val result = arrayListOf<Int>()

            fun Node.dfs() {
                result.add(`val`)

                children.forEach {
                    it?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().preorder(null)

    }
}