package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfLines(widths: IntArray, s: String): IntArray {
            val result = intArrayOf(0, 0)
            if (s.isEmpty()) {
                return result
            }

            result[0]++

            s.forEach {
                val width = widths[it - 'a']

                if (result[1] + width > 100) {
                    result[0]++
                    result[1] = width
                } else {
                    result[1] += width
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numberOfLines(
            intArrayOf(), ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}