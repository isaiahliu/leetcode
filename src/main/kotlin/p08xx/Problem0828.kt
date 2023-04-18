package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun uniqueLetterString(s: String): Int {
            val pos = Array(26) { arrayListOf<Int>() }

            s.forEachIndexed { index, c ->
                pos[c - 'A'].add(index)
            }

            var result = 0
            pos.forEach {
                it.forEachIndexed { index, p ->
                    val left = p - (it.getOrNull(index - 1) ?: -1)
                    val right = (it.getOrNull(index + 1) ?: s.length) - p

                    result += left * right
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().uniqueLetterString(
            "ABC"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}