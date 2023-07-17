package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distinctEchoSubstrings(text: String): Int {
            val result = hashSetOf<String>()

            fun match(start1: Int, start2: Int, size: Int): Boolean {
                repeat(size) {
                    if (text[start1 + it] != text[start2 + it]) {
                        return false
                    }
                }

                return true
            }

            var size = 1

            while (size * 2 <= text.length) {
                for (i in 0 until text.length - size * 2 + 1) {
                    if (match(i, i + size, size)) {
                        result.add(text.substring(i until i + size))
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

