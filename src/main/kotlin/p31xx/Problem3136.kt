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
            val lowercases = 'a'..'z'
            val uppercases = 'A'..'Z'
            val digits = '0'..'9'
            word.forEach {
                when (it) {
                    in vowels -> hasVowel = true
                    in lowercases, in uppercases -> hasConsonant = true
                    !in digits -> return false
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
