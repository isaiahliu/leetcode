package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun uniqueOccurrences(arr: IntArray): Boolean {
            return arr.toList().groupingBy { it }.eachCount().values.let { it.size == it.toSet().size }
        }
    }

    measureTimeMillis {
        Solution().uniqueOccurrences(
            intArrayOf(1, 2, 2, 1, 1, 3)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
