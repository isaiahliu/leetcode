package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun convertToBase7(num: Int): String {
            return num.toString(7)
        }
    }

    measureTimeMillis {
        Solution().convertToBase7(
            100
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}