package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minTimeToType(word: String): Int {
            var cur = 'a'

            var result = 0

            word.forEach {
                result++

                result += (cur.coerceAtLeast(it) - cur.coerceAtMost(it)).let { it.coerceAtMost(26 - it) }

                cur = it
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minTimeToType(
            "bza"
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}