package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSubstrings(s: String): Int {
            var result = s.length
            s.dropLast(1).forEachIndexed { index, c ->
                var size = 1
                while (true) {
                    val left = s.getOrNull(index - size) ?: break
                    val right = s.getOrNull(index + size) ?: break

                    if (left == right) {
                        result++
                        size++
                    } else {
                        break
                    }
                }

                if (c == s[index + 1]) {
                    result++

                    size = 1

                    while (true) {
                        val left = s.getOrNull(index - size) ?: break
                        val right = s.getOrNull(index + 1 + size) ?: break

                        if (left == right) {
                            result++
                            size++
                        } else {
                            break
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countSubstrings(
            "abc"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}