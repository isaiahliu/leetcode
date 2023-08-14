package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun areSentencesSimilar(sentence1: String, sentence2: String): Boolean {
            val words1 = sentence1.split(" ")
            val words2 = sentence2.split(" ")

            val maxWords = if (words1.size > words2.size) words1 else words2
            val minWords = if (words1.size > words2.size) words2 else words1

            var left = -1
            var right = minWords.size

            while (left < minWords.lastIndex && maxWords[left + 1] == minWords[left + 1]) {
                left++
            }

            while (right > 0 && right > left && maxWords[maxWords.lastIndex - minWords.lastIndex + right - 1] == minWords[right - 1]) {
                right--
            }

            return right - left <= 1
        }
    }

    measureTimeMillis {
        Solution().areSentencesSimilar(
            "xD iP tqchblXgqvNVdi", "FmtdCzv Gp YZf UYJ xD iP tqchblXgqvNVdi"
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
