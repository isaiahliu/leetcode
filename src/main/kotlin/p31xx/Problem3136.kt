package p31xx

import util.expect

fun main() {
    class Solution {
        fun isValid(word: String): Boolean {
            if (word.length < 3) {
                return false
            }

            var hasVowel = false
            var hasConsonant = false

            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
            word.forEach {
                when (it) {
                    in vowels -> hasVowel = true
                    in 'a'..'z' -> hasConsonant = true
                    in 'A'..'Z' -> hasConsonant = true
                    !in '0'..'9' -> return false
                }
            }

            return hasVowel && hasConsonant
        }
    }

    expect {
        Solution().isValid(
            ""
        )
    }
}
