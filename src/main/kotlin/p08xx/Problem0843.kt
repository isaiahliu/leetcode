package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Master {
        fun guess(word: String): Int {
            return 2
        }
    }

    class Solution {
        fun findSecretWord(words: Array<String>, master: Master) {
            val range = 0 until 6

            fun String.matchSize(target: String): Int {
                return range.count { this[it] == target[it] }
            }

            val wordGroups = hashMapOf<String, MutableMap<Int, MutableSet<String>>>()

            val groupCounts = IntArray(words.size)
            var nextWord = words[0]
            var maxGroupCount = 0

            for (i in words.indices) {
                for (j in i + 1 until words.size) {
                    val word1 = words[i]
                    val word2 = words[j]

                    word1.matchSize(word2).also {
                        wordGroups.computeIfAbsent(word1) {
                            hashMapOf()
                        }.computeIfAbsent(it) {
                            groupCounts[i]++
                            hashSetOf()
                        }.add(word2)

                        wordGroups.computeIfAbsent(word2) {
                            hashMapOf()
                        }.computeIfAbsent(it) {
                            groupCounts[j]++
                            hashSetOf()
                        }.add(word1)

                        if (groupCounts[j] > maxGroupCount) {
                            maxGroupCount = groupCounts[j]
                            nextWord = word2
                        }
                    }

                    if (groupCounts[i] > maxGroupCount) {
                        maxGroupCount = groupCounts[i]
                        nextWord = word1
                    }
                }
            }

            while (true) {
                val match = master.guess(nextWord)

                if (match == 6) {
                    break
                }

                maxGroupCount = -1

                wordGroups[nextWord]?.get(match)?.also { possibleWords ->
                    possibleWords.forEach { possibleWord ->
                        val index = words.indexOf(possibleWord)
                        wordGroups[possibleWord]?.onEach { (_, value) ->
                            if (value.isNotEmpty()) {
                                value.retainAll(possibleWords)
                                if (value.isEmpty()) {
                                    groupCounts[index]--
                                }
                            }
                        }

                        if (groupCounts[index] > maxGroupCount) {
                            maxGroupCount = groupCounts[index]
                            nextWord = possibleWord
                        }
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().findSecretWord(
            arrayOf("acckzz", "ccbazz", "eiowzz", "abcczz"), Master()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}