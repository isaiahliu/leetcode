package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseWords(s: String): String {
            return s.split(" ").joinToString(" ") { it.reversed() }
        }
    }

    measureTimeMillis {
        Solution().reverseWords(
            "Let's take LeetCode contest"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}