package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxRepOpt1(text: String): Int {
            var result = 1

            fun process(str: String) {
                val counts = IntArray(26)

                str.forEach { counts[it - 'a']++ }

                for (i in 0 until str.lastIndex) {
                    val c = str[i]

                    var remainingCount = counts[c - 'a'] - 1

                    var count = 1
                    var skipped = false

                    var index = i + 1
                    while (remainingCount > 0 && index < str.length) {
                        if (str[index++] != c) {
                            if (skipped) {
                                break
                            } else {
                                skipped = true
                            }
                        }
                        remainingCount--
                        count++
                    }

                    result = result.coerceAtLeast(count)
                }
            }

            process(text)
            process(text.reversed())

            return result
        }
    }

    measureTimeMillis {
        Solution().maxRepOpt1(
            "bbababaaaa"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
