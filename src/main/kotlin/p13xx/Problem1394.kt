package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLucky(arr: IntArray): Int {
            return arr.toList().groupingBy { it }.eachCount().filter { it.key == it.value }.keys.maxOrNull() ?: -1
        }
    }

    measureTimeMillis {
        Solution().findLucky(
            intArrayOf(4, 1, 3),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

