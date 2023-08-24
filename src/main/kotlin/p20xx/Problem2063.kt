package p20xx

import util.expect

fun main() {
    class Solution {
        fun countVowels(word: String): Long {
            var result = 0L

            val vowels = setOf('a', 'e', 'i', 'o', 'u')

            val sum = word.length.toLong().let { it * (it + 1) / 2 }

            word.forEachIndexed { index, c ->
                if (c in vowels) {
                    val left = index.toLong().let { it * (it + 1) / 2 }
                    val right = (word.lastIndex - index).toLong().let { it * (it + 1) / 2 }
                    result += sum - left - right
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