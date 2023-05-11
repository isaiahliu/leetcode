package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun queryString(s: String, n: Int): Boolean {
            if (n == 0) {
                return true
            }

            val b = n.toString(2)

            if (b !in s) {
                return false
            }

            return queryString(s, n - 1)
        }
    }

    measureTimeMillis {
        Solution().queryString(
            "100", 100
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
