package p15xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().findSmallestSetOfVertices(
            9466441, listOf()
        ).also { println(it) }
    }
}

