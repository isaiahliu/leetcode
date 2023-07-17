package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfSteps(num: Int): Int {
            return when {
                num == 0 -> 0
                num % 2 == 0 -> numberOfSteps(num / 2) + 1
                else -> numberOfSteps(num - 1) + 1
            }
        }
    }

    measureTimeMillis {
        Solution().numberOfSteps(
            14
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

