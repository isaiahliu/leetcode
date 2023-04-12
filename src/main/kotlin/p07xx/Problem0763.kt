package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun partitionLabels(s: String): List<Int> {
            val pos = IntArray(26)

            s.forEachIndexed { index, c ->
                pos[c - 'a'] = index
            }

            val result = arrayListOf<Int>()
            var startIndex = 0

            while (startIndex < s.length) {
                var rangeEnd = pos[s[startIndex] - 'a']
                var endIndex = startIndex + 1

                while (endIndex <= rangeEnd) {
                    rangeEnd = rangeEnd.coerceAtLeast(pos[s[endIndex++] - 'a'])
                }

                result.add(endIndex - startIndex)
                startIndex = endIndex
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().partitionLabels(
            "eccbbbbdec"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}