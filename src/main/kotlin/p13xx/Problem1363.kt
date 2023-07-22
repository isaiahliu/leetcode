package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestMultipleOfThree(digits: IntArray): String {
            digits.sortDescending()

            val m = digits.fold(0) { a, b ->
                (a + b) % 3
            }

            val result: String

            if (m == 0) {
                result = digits.joinToString("")
            } else {
                val ignoreIndices1 = hashSetOf<Int>()
                val ignoreIndices2 = hashSetOf<Int>()

                loop@ for (index in digits.lastIndex downTo 0) {
                    when {
                        digits[index] % 3 == m -> {
                            ignoreIndices1.add(index)
                            break@loop
                        }

                        ignoreIndices2.size < 2 && digits[index] % 3 == 3 - m -> {
                            ignoreIndices2.add(index)
                        }
                    }
                }

                val ignoreIndices = hashSetOf<Int>()
                if (ignoreIndices1.isNotEmpty()) {
                    ignoreIndices.addAll(ignoreIndices1)
                } else if (ignoreIndices2.size == 2) {
                    ignoreIndices.addAll(ignoreIndices2)
                } else {
                    return ""
                }

                result = digits.mapIndexed { index, i ->
                    if (index in ignoreIndices) {
                        ""
                    } else {
                        i.toString()
                    }
                }.joinToString("").ifEmpty {
                    return ""
                }
            }

            return result.takeIf { it[0] > '0' }.orEmpty().ifEmpty { "0" }
        }
    }

    measureTimeMillis {
        Solution().largestMultipleOfThree(
            intArrayOf(1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

