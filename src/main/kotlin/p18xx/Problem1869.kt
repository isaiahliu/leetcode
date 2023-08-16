package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkZeroOnes(s: String): Boolean {
            val result = intArrayOf(0, 0)
            val length = intArrayOf(0, 0)

            var pre = s[0]
            s.forEach {
                if (it == pre) {
                    length[it - '0']++
                } else {
                    pre = it
                    length[it - '0'] = 1
                }
                result[it - '0'] = result[it - '0'].coerceAtLeast(length[it - '0'])
            }

            return result[1] > result[0]
        }
    }

    measureTimeMillis {
        Solution().checkZeroOnes(
            ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
