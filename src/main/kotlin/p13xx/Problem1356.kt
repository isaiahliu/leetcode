package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortByBits(arr: IntArray): IntArray {
            return arr.sortedWith(compareBy<Int> { Integer.bitCount(it) }.thenBy { it }).toIntArray()
        }
    }

    measureTimeMillis {
        Solution().sortByBits(
            intArrayOf(1, 1000000000)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

