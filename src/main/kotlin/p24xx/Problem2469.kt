package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun convertTemperature(celsius: Double): DoubleArray {
            return doubleArrayOf(celsius + 273.15, celsius * 1.8 + 32.0)
        }
    }

    measureTimeMillis {
        Solution().convertTemperature(
            15.0
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}