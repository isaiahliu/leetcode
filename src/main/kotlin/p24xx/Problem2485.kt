package p24xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun pivotInteger(n: Int): Int {
            val sum = n * (n + 1) / 2
            return sqrt(sum.toDouble()).toInt().takeIf { it * it == sum } ?: -1
        }
    }

    measureTimeMillis {
        Solution().pivotInteger(
            8
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}