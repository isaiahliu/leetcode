package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun twoCitySchedCost(costs: Array<IntArray>): Int {
            costs.sortBy { it[0] - it[1] }

            val halfLength = costs.size / 2

            var result = 0

            repeat(halfLength) {
                result += costs[it][0]
                result += costs[halfLength + it][1]
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().twoCitySchedCost(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
