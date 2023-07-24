package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun generateTheString(n: Int): String {
            return if (n % 2 == 0) {
                "a".repeat(n - 1) + "b".repeat(1)
            } else {
                "a".repeat(n)
            }
        }
    }

    measureTimeMillis {
        Solution().generateTheString(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

