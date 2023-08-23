package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun smallestSubsequence(s: String, k: Int, letter: Char, repetition: Int): String {
            val indexList = Array(26) {
                LinkedList<Int>()
            }

            val counts = IntArray(s.length)

            var t = 0
            for (index in s.lastIndex downTo 0) {
                if (s[index] == letter) {
                    t++
                }

                counts[index] = t
                indexList[s[index] - 'a'].push(index)
            }

            val result = StringBuilder()
            var currentIndex = -1
            var remainRepetition = repetition
            repeat(k) {
                if (remainRepetition == k - result.length) {
                    result.append(letter)
                    remainRepetition--
                } else {
                    for ((index, indices) in indexList.withIndex()) {
                        while (indices.isNotEmpty() && indices.peek() <= currentIndex) {
                            indices.poll()
                        }

                        indices.peek()?.takeIf { counts[it] >= remainRepetition && s.length - it >= k - result.length }
                            ?.also {
                                result.append('a' + index)
                                indices.poll()
                                currentIndex = it

                                if ('a' + index == letter) {
                                    remainRepetition--
                                }
                            } ?: continue

                        break
                    }
                }
            }
            return result.toString()
        }
    }

    expect {
        Solution().smallestSubsequence(
            "aaabbbcccddd", 3, 'b', 2
        )
    }
}