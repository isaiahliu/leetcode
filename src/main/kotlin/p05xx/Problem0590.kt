package p05xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun postorder(root: Node?): List<Int> {
            val result = arrayListOf<Int>()

            fun Node.dfs() {
                children.forEach {
                    it?.dfs()
                }
                result.add(`val`)
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().postorder(null)

    }
}