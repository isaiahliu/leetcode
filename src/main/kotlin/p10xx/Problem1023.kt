package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun camelMatch(queries: Array<String>, pattern: String): List<Boolean> {
            return queries.map {
                var l = 0
                var r = 0

                while (l < it.length && r < pattern.length) {
                    if (it[l] == pattern[r]) {
                        r++
                    } else if (it[l] !in 'a'..'z') {
                        return@map false
                    }

                    l++
                }

                if (r < pattern.length) {
                    return@map false
                }

                for (i in l until it.length) {
                    if (it[i] !in 'a'..'z') {
                        return@map false
                    }
                }

                true
            }
        }
    }

    measureTimeMillis {
        Solution().camelMatch(
            arrayOf(), ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}