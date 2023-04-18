package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largeGroupPositions(s: String): List<List<Int>> {
            val result = arrayListOf<List<Int>>()
            var pre = ' '
            var start = -1
            var end = -1

            "$s ".forEachIndexed { index, c ->
                if (c == pre) {
                    end = index
                } else {
                    if (end - start >= 2) {
                        result.add(listOf(start, end))
                    }

                    start = index

                    pre = c
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().largeGroupPositions(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}