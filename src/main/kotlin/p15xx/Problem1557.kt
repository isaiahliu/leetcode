package p15xx

import util.expect

fun main() {
    class Solution {
        fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
            val roots = (0 until n).toMutableSet()

            edges.forEach { (_, to) ->
                roots.remove(to)
            }

            return roots.toList()
        }
    }

    expect {
        Solution().findSmallestSetOfVertices(
            9466441, listOf()
        )
    }
}

