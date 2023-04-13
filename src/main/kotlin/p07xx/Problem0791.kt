package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun customSortString(order: String, s: String): String {
            val orderMap = order.mapIndexed { index, c -> c to index }.toMap()

            return s.toCharArray().sortedWith(compareBy { orderMap[it] ?: 0 }).toCharArray().let { String(it) }
        }
    }

    measureTimeMillis {
        Solution().customSortString(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}