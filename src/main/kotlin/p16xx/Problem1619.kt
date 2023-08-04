package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun trimMean(arr: IntArray): Double {
            return arr.sorted().slice(arr.size / 20 until arr.size - arr.size / 20).average()
        }
    }

    measureTimeMillis {
        Solution().trimMean(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}