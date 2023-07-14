package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minCostToMoveChips(position: IntArray): Int {
            val counts = intArrayOf(0, 0)

            position.forEach {
                counts[it % 2]++
            }

            return counts.min()
        }
    }

    measureTimeMillis {
        Solution().minCostToMoveChips(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
