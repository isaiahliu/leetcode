package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheDifference(s: String, t: String): Char {
            val counts = IntArray(26)

            t.forEach { counts[it - 'a']++ }
            s.forEach { counts[it - 'a']-- }

            return 'a' + counts.indexOfFirst { it > 0 }
        }
    }

    measureTimeMillis {
        Solution().findTheDifference(
            "abcd", "abcde"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

