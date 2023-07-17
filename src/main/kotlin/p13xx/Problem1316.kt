package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distinctEchoSubstrings(text: String): Int {
            val result = hashSetOf<String>()

            var size = 1

            while (size * 2 <= text.length) {
                for (i in 0 until text.length - size * 2 + 1) {
                    val first = text.substring(i until i + size)
                    val second = text.substring(i + size until i + size * 2)
                    if (first == second) {
                        result.add(first)
                    }
                }

                size++
            }

            return result.size
        }
    }

    measureTimeMillis {
        Solution().distinctEchoSubstrings(
            "abcabcabc"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

