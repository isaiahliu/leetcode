package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun titleToNumber(columnTitle: String): Int {
            var result = 0

            columnTitle.forEach {
                result = result * 26 + (it - 'A') + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().titleToNumber("ZY").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

