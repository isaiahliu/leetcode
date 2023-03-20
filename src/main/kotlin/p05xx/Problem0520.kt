package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun detectCapitalUse(word: String): Boolean {
            if (word.length == 1) {
                return true
            }

            val lowerRange = 'a'..'z'
            val requireLower = word[0] in lowerRange || word[1] in lowerRange

            for (c in word.drop(1)) {
                val lowerCase = c in lowerRange

                if (requireLower && !lowerCase || !requireLower && lowerCase) {
                    return false
                }
            }

            return true
        }

        fun detectCapitalUse2(word: String): Boolean {
            var upperCaseCount = 0
            var lowerCaseCount = 0

            word.forEach {
                when (it) {
                    in 'a'..'z' -> lowerCaseCount++
                    in 'A'..'Z' -> upperCaseCount++
                }
            }

            return upperCaseCount == word.length || lowerCaseCount == word.length || word[0] in 'A'..'Z' && upperCaseCount == 1
        }
    }

    measureTimeMillis {
        Solution().detectCapitalUse("ABC").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}