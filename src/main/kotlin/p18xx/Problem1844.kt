package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun replaceDigits(s: String): String {
            return s.fold(StringBuilder()) { str, c ->
                str.append(
                    when (c) {
                        in 'a'..'z' -> c
                        else -> str.last() + (c - '0')
                    }
                )
            }.toString()
        }
    }

    measureTimeMillis {
        Solution().replaceDigits(
            "a1c1e1"
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
