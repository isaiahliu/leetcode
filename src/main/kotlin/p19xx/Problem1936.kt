package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun addRungs(rungs: IntArray, dist: Int): Int {
            var result = 0

            var pre = 0
            rungs.forEach {
                result += (it - pre - 1).coerceAtLeast(0) / dist
                pre = it
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().addRungs(
            intArrayOf(), 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}