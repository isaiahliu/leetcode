package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfSubstrings(s: String): Int {
            var result = 0

            val last = intArrayOf(-1, -1, -1)

            s.forEachIndexed { index, c ->
                val pos = c - 'a'

                result += last[(pos + 1) % 3].coerceAtMost(last[(pos + 2) % 3]) + 1

                last[pos] = index
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numberOfSubstrings(
            "abcabc"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

