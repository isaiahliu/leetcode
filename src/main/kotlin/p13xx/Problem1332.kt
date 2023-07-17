package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun removePalindromeSub(s: String): Int {
            return when {
                s.isEmpty() -> 0
                s == s.reversed() -> 1
                else -> 2
            }
        }
    }

    measureTimeMillis {
        Solution().removePalindromeSub(
            "ababa"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

