package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findCenter(edges: Array<IntArray>): Int {
            return edges[0].intersect(edges[1].toSet()).first()
        }
    }

    measureTimeMillis {
        Solution().findCenter(
            arrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
