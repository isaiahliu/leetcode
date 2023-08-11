package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun beautySum(s: String): Int {
            var result = 0
            for (i in s.indices) {
                val counts = IntArray(26)
                counts[s[i] - 'a']++
                var min = 1
                var max = 1
                for (j in i + 1 until s.length) {
                    counts[s[j] - 'a']++

                    if (counts[s[j] - 'a'] == 1) {
                        min = 1
                    }

                    if (counts[s[j] - 'a'] == min + 1) {
                        min = counts.filter { it > 0 }.min()
                    }

                    if (counts[s[j] - 'a'] > max) {
                        max = counts[s[j] - 'a']
                    }

                    result += max - min
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().beautySum(
            "aabcb"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
