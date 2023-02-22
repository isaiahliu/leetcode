package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun convertToTitle(columnNumber: Int): String {
            var result = ""

            var t = columnNumber
            while (true) {
                t--

                result = "${'A' + t % 26}${result}"

                if (t < 26) {
                    break
                }

                t /= 26
            }

            return result
        }
    }
    measureTimeMillis {
        repeat(100) {
            Solution().convertToTitle(it + 1).also { println(it) }
        }
    }.also { println("Time cost: ${it}ms") }
}

