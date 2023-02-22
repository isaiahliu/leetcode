package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseWords(s: String): String {
            return s.split(" ").filter { it.isNotEmpty() }.reversed().joinToString(" ")
        }
    }

    measureTimeMillis {
        Solution().reverseWords(
            ""
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

