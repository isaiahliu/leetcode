package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun repeatedStringMatch(a: String, b: String): Int {
            for (i in (b.length / a.length).coerceAtLeast(1) until b.length / a.length + 3) {
                if (b in a.repeat(i)) {
                    return i
                }
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().repeatedStringMatch(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}