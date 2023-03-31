package p06xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun judgeSquareSum(c: Int): Boolean {
            var num = 0
            while (true) {
                val numSquare = num * num
                if (numSquare > c / 2) {
                    return false
                }

                val rem = c - numSquare
                sqrt(rem.toDouble()).toInt().takeIf { it * it == rem }?.also {
                    return true
                }

                num++
            }
        }
    }

    measureTimeMillis {
        Solution().judgeSquareSum(
            5
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}