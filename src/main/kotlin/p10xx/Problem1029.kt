package p10xx

import util.expect

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

    expect {
        Solution().twoCitySchedCost(
            arrayOf()
        )
    }
}
