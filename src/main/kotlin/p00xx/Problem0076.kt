package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minWindow(s: String, t: String): String {
            if (t.length > s.length) {
                return ""
            }

            if (t.length == 1) {
                return if (t in s) t else ""
            }

            val unmatchedCounts = hashMapOf<Char, Int>()

            t.forEach {
                unmatchedCounts[it] = (unmatchedCounts[it] ?: 0) + 1
            }

            var unmatchedChars = unmatchedCounts.size

            var leftIndex = 0
            while (leftIndex < s.length && s[leftIndex] !in t) {
                leftIndex++
            }

            var resultIndex = 0
            var resultLength = Int.MAX_VALUE

            var rightIndex = leftIndex
            while (rightIndex < s.length) {
                val c = s[rightIndex]

                unmatchedCounts[c]?.also { cCount ->
                    unmatchedCounts[c] = cCount - 1
                    if (cCount == 1) {
                        unmatchedChars--
                    }

                    if (unmatchedChars == 0) {
                        var foundNextChar = false
                        while (!foundNextChar) {
                            val leftC = s[leftIndex]
                            unmatchedCounts[leftC]?.also {
                                if (unmatchedChars == 0) {
                                    (rightIndex - leftIndex + 1).takeIf { it < resultLength }?.also {
                                        resultIndex = leftIndex
                                        resultLength = it
                                    }

                                    unmatchedCounts[leftC] = it + 1
                                    if (it == 0) {
                                        unmatchedChars++
                                    }

                                    leftIndex++
                                } else {
                                    foundNextChar = true
                                }
                            } ?: leftIndex++
                        }
                    }
                }
                rightIndex++
            }

            return if (resultLength == Int.MAX_VALUE) {
                ""
            } else {
                s.substring(resultIndex, resultIndex + resultLength)
            }
        }
    }

    measureTimeMillis {
        println(Solution().minWindow("bbb", "aaaa"))
    }.also { println("Time cost: ${it}ms") }
}

