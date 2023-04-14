package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun camelMatch(queries: Array<String>, pattern: String): List<Boolean> {
            return queries.map {
                var r = 0

                it.forEach {
                    when (it) {
                        pattern.getOrNull(r) -> {
                            r++
                        }

                        !in 'a'..'z' -> {
                            return@map false
                        }
                    }
                }

                r == pattern.length
            }
        }
    }

    measureTimeMillis {
        Solution().camelMatch(
            arrayOf(), ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}