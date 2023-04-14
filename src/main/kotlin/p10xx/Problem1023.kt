package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun camelMatch(queries: Array<String>, pattern: String): List<Boolean> {
            return queries.map {
                var l = 0
                var r = 0

                while (l < it.length) {
                    when {
                        it[l] == pattern.getOrNull(r) -> {
                            r++
                        }

                        it[l] !in 'a'..'z' -> {
                            return@map false
                        }
                    }

                    l++
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