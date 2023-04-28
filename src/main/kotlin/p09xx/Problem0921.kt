package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minAddToMakeValid(s: String): Int {
            var count = 0
            var result = 0

            s.forEach {
                when (it) {
                    '(' -> {
                        count++
                    }

                    ')' -> {
                        if (count == 0) {
                            result++
                        } else {
                            count--
                        }
                    }
                }
            }

            return result + count
        }
    }

    measureTimeMillis {
        Solution().minAddToMakeValid(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}