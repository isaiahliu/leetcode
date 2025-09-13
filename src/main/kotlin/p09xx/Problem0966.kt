package p09xx

import util.expect

fun main() {
    class Solution {
        fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
            val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

            val STRICT = 0
            val IGNORE_CASE = 1
            val IGNORE_VOWELS = 2

            class Trie {
                val children by lazy { hashMapOf<Char, Trie>() }

                var wordIndex: Int? = null

                fun mark(wordIndex: Int, index: Int) {
                    wordlist[wordIndex].getOrNull(index)?.also {
                        children.computeIfAbsent(it) { Trie() }.mark(wordIndex, index + 1)
                    } ?: run {
                        this.wordIndex = this.wordIndex ?: wordIndex
                    }
                }

                fun query(word: String, index: Int, type: Int): Pair<Int, Int>? {
                    if (word.length == index) {
                        return wordIndex?.let { type to it }
                    }

                    var result: Pair<Int, Int>? = null
                    (0..IGNORE_VOWELS).forEach {
                        when (it) {
                            STRICT -> {
                                result = children[word[index]]?.query(word, index + 1, maxOf(type, STRICT))
                            }

                            IGNORE_CASE -> {
                                if ((result?.first ?: it) < it) {
                                    return@forEach
                                }
                                arrayOf(word[index].uppercaseChar(), word[index].lowercaseChar()).mapNotNull {
                                    children[it]?.query(word, index + 1, maxOf(type, IGNORE_CASE))
                                }.minWithOrNull(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })?.also {
                                    if (it.first < (result?.first ?: Int.MAX_VALUE)) {
                                        result = it
                                    } else if (it.first == result?.first && it.second < (result?.second ?: Int.MAX_VALUE)) {
                                        result = it
                                    }
                                }
                            }

                            IGNORE_VOWELS -> {
                                if (word[index] in vowels) {
                                    vowels.mapNotNull {
                                        children[it]?.query(word, index + 1, maxOf(type, IGNORE_VOWELS))
                                    }.minWithOrNull(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })?.also {
                                        if (it.first < (result?.first ?: Int.MAX_VALUE)) {
                                            result = it
                                        } else if (it.first == result?.first && it.second < (result?.second ?: Int.MAX_VALUE)) {
                                            result = it
                                        }
                                    }
                                }
                            }
                        }
                    }

                    return result
                }
            }

            val root = Trie()
            wordlist.indices.forEach {
                root.mark(it, 0)
            }

            return Array(queries.size) {
                root.query(queries[it], 0, STRICT)?.second?.let { wordlist[it] }.orEmpty()
            }
        }
    }

    expect {
        Solution().spellchecker(
            arrayOf("iiy", "aay"), arrayOf("uay")
        ).toList()
    }
}
