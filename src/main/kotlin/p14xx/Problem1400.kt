package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun canConstruct(s: String, k: Int): Boolean {
            return k in s.groupingBy { it }.eachCount().values.count { it % 2 == 1 }..s.length
        }
    }

    measureTimeMillis {
        Solution().canConstruct(
            "annabelle", 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

