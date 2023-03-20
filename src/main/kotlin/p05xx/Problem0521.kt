package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findLUSlength(a: String, b: String): Int {
            if (a == b) {
                return -1
            }

            return a.length.coerceAtLeast(b.length)
        }
    }

    measureTimeMillis {
        Solution().findLUSlength("ABC", "").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}