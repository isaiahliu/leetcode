package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxDepth(s: String): Int {
            var result = 0
            var depth = 0
            s.forEach {
                when (it) {
                    '(' -> {
                        depth++
                        result = result.coerceAtLeast(depth)
                    }

                    ')' -> {
                        depth--
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxDepth(
            ""
        ).also { println("${it} should be ${it}") }
    }
}

