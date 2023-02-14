package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun multiply(num1: String, num2: String): String {
            val result = IntArray(40001)

            var maxDigits = 0
            num1.reversed().forEachIndexed { leftIndex, left ->
                num2.reversed().forEachIndexed { rightIndex, right ->
                    (leftIndex + rightIndex).also {
                        maxDigits = maxDigits.coerceAtLeast(it)
                        result[it] += (left - '0') * (right - '0')
                    }
                }
            }

            for (i in 0..maxDigits) {
                result[i + 1] += result[i] / 10
                result[i] %= 10
            }

            var t = maxDigits + 1
            while (result[t] > 0) {
                result[t + 1] += result[t] / 10
                result[t] %= 10

                t++
            }

            val str = result.take(t).reversed().joinToString("").trimStart('0')
            return str.ifBlank { "0" }
        }
    }

    measureTimeMillis {
        println(Solution().multiply("408", "5"))
    }.also { println("Time cost: ${it}ms") }
}


