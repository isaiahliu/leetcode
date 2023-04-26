package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun atMostNGivenDigitSet(digits: Array<String>, n: Int): Int {
            fun calculate(s: String): Int {
                var result = 0

                var b = 1

                repeat(s.length - 1) {
                    b *= digits.size
                }

                for (digit in digits) {
                    if (digit[0] < s[0]) {
                        result += b
                    } else if (digit[0] == s[0]) {
                        s.drop(1).takeIf { it.isNotEmpty() }?.also {
                            result += calculate(it)
                        } ?: run {
                            result++
                        }
                    } else {
                        break
                    }
                }

                return result
            }

            val str = n.toString()
            var result = calculate(str)

            if (digits.size == 1) {
                result += str.length - 1
            } else {
                var b = 1

                repeat(str.length) {
                    b *= digits.size
                }

                b--
                b /= (digits.size - 1)
                b -= 1

                result += b
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().atMostNGivenDigitSet(
            arrayOf("3", "5"), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}