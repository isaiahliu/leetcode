package p07xx

import util.expect

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

    expect {
        Solution().nextGreatestLetter(
            charArrayOf('c'), 'a'
        )
    }
}