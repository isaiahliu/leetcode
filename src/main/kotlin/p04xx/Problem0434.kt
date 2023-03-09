package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSegments(s: String): Int {
            var result = 0

            var wordLength = 0
            ("$s ").forEach {
                when (it) {
                    ' ' -> {
                        if (wordLength > 0) {
                            result++
                        }
                        wordLength = 0
                    }

                    else -> {
                        wordLength++
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countSegments("").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


