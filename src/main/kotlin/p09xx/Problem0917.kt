package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseOnlyLetters(s: String): String {
            val result = StringBuilder()

            var r = s.lastIndex

            val lowerRange = 'a'..'z'
            val upperRange = 'A'..'Z'
            s.forEachIndexed { index, c ->
                if (c in lowerRange || c in upperRange) {
                    while (s[r] !in lowerRange && s[r] !in upperRange) {
                        r--
                    }

                    result.append(s[r--])
                } else {
                    result.append(c)
                }
            }

            return result.toString()
        }
    }

    measureTimeMillis {
        Solution().reverseOnlyLetters(
            "ab-cd"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}