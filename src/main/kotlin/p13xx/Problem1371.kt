package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheLongestSubstring(s: String): Int {
            val map = hashMapOf(0 to -1)

            var status = 0
            var result = 0

            s.forEachIndexed { index, c ->
                status = when (c) {
                    'a' -> status xor (1 shl 0)
                    'e' -> status xor (1 shl 1)
                    'i' -> status xor (1 shl 2)
                    'o' -> status xor (1 shl 3)
                    'u' -> status xor (1 shl 4)
                    else -> status
                }

                map[status]?.also {
                    result = result.coerceAtLeast(index - it)
                } ?: run {
                    map[status] = index
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().findTheLongestSubstring(
            "eleetminicoworoep"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

