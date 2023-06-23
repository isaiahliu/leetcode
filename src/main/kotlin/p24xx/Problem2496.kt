package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximumValue(strs: Array<String>): Int {
            return strs.maxOf { it.toIntOrNull() ?: it.length }
        }
    }

    measureTimeMillis {
        Solution().maximumValue(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}