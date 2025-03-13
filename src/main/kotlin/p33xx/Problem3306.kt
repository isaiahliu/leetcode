package p33xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countOfSubstrings(word: String, k: Int): Long {
            val vowels = mapOf('a' to 0, 'e' to 1, 'i' to 2, 'o' to 3, 'u' to 4)
            var left = 0
            var right = 0

            var result = 0L

            var consonantCount = 0
            var lastConsonantIndex = 0
            var vowelIndices = Array(5) { LinkedList<Int>() }

            fun moveRight() {
                vowels[word[right]]?.also {
                    vowelIndices[it] += right
                } ?: run {
                    lastConsonantIndex = right
                    consonantCount++
                }

                right++
            }

            fun moveLeft() {
                vowels[word[left++]]?.also {
                    vowelIndices[it].pollFirst()
                } ?: run {
                    consonantCount--
                }
            }

            while (right < word.length) {
                while (right < word.length && (word[right] in vowels || consonantCount < k || vowelIndices.any { it.isEmpty() })) {
                    moveRight()
                }

                while (left < right && consonantCount >= k) {
                    if (vowelIndices.all { it.isNotEmpty() } && consonantCount == k) {
                        val rightMost = maxOf(lastConsonantIndex, vowelIndices.maxOf { it.peekFirst() })

                        result += right - rightMost
                    }

                    moveLeft()
                }
            }

            return result
        }
    }

    expect {
        Solution().countOfSubstrings(
            "aeouih", 0
        )
    }
}
