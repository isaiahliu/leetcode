package p17xx

import util.expect

fun main() {
    class Solution {
        fun largestMerge(word1: String, word2: String): String {
            val result = StringBuilder()

            val words = arrayOf(word1, word2)
            val indices = intArrayOf(0, 0)

            fun append(index: Int): Boolean {
                result.append(words[index].getOrNull(indices[index]++) ?: return false)

                return true
            }

            while (indices[0] < word1.length && indices[1] < word2.length) {
                when {
                    word1[indices[0]] > word2[indices[1]] -> {
                        append(0)
                    }

                    word1[indices[0]] < word2[indices[1]] -> {
                        append(1)
                    }

                    else -> {
                        val firstChar = word1[indices[0]]

                        val t = intArrayOf(indices[0] + 1, indices[1] + 1)

                        var popIndex = 0
                        loop@ while (true) {
                            popIndex = when {
                                t[0] == words[0].length -> 1
                                t[1] == words[1].length -> 0
                                words[0][t[0]] < firstChar -> 1
                                words[1][t[1]] < firstChar -> 0
                                words[0][t[0]] > words[1][t[1]] -> 0
                                words[0][t[0]] < words[1][t[1]] -> 1
                                else -> {
                                    t[0]++
                                    t[1]++
                                    continue@loop
                                }
                            }

                            break
                        }

                        var foundLarger = false
                        loop@ while (indices[popIndex] < words[popIndex].length) {
                            when {
                                words[popIndex][indices[popIndex]] > firstChar -> {
                                    append(popIndex)
                                    foundLarger = true
                                }

                                words[popIndex][indices[popIndex]] == firstChar && !foundLarger -> {
                                    append(popIndex)
                                }

                                else -> {
                                    break@loop
                                }
                            }
                        }
                    }
                }
            }

            words.indices.forEach {
                while (append(it)) {
                    //Do nothing
                }
            }

            return result.toString()
        }
    }

    expect {
        Solution().largestMerge(
            "abac", "abad"
        )
    }
}
