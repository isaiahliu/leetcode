package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestOddNumber(num: String): String {
            for (i in num.lastIndex downTo 0) {
                if ((num[i] - '0') % 2 == 1) {
                    return num.take(i + 1)
                }
            }

            return ""
        }
    }

    measureTimeMillis {
        Solution().largestOddNumber(
            ""
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
