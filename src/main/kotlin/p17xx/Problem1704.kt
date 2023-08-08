package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun halvesAreAlike(s: String): Boolean {
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

            val counts = intArrayOf(0, 0)

            s.forEachIndexed { index, c ->
                if (c in vowels) {
                    counts[index / (s.length / 2)]++
                }
            }

            return counts[0] == counts[1]
        }
    }

    measureTimeMillis {
        Solution().halvesAreAlike(
            "aaab"
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
