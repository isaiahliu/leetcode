package p20xx

import util.expect

fun main() {
    class Solution {
        fun countVowels(word: String): Long {
            var result = 0L

            val vowels = setOf('a', 'e', 'i', 'o', 'u')

            word.forEachIndexed { index, c ->
                if (c in vowels) {
                    result += 1L * (index + 1) * (word.length - index)
                }
            }

            return result
        }
    }

    expect {
        Solution().countVowels(
            "aba"
        )
    }
}