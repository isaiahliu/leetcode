package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxRepeating(sequence: String, word: String): Int {
            var result = 0

            while (word.repeat(result + 1) in sequence) {
                result++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxRepeating(
            "", ""
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

