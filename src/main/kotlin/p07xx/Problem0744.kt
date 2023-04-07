package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nextGreatestLetter(letters: CharArray, target: Char): Char {
            var result: Char? = null

            letters.forEach {
                if (it in target + 1 until (result ?: (it + 1))) {
                    result = it
                }
            }

            return result ?: letters[0]
        }
    }

    measureTimeMillis {
        Solution().nextGreatestLetter(
            charArrayOf('c'), 'a'
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}