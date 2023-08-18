package p22xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sum(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }

    measureTimeMillis {
        Solution().sum(
            1, 1
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}