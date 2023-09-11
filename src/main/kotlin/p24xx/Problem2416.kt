package p24xx

import util.expect

fun main() {
    class Solution {
        fun sumPrefixScores(words: Array<String>): IntArray {
            class Trie {
                val children = hashMapOf<Char, Trie>()

                var size = 0

                fun add(word: String, index: Int) {
                    size++

                    word.getOrNull(index)?.also {
                        children.computeIfAbsent(it) { Trie() }.add(word, index + 1)
                    }
                }

                fun sum(word: String, index: Int): Int {
                    var result = size

                    word.getOrNull(index)?.let {
                        children[it]?.sum(word, index + 1)?.also {
                            result += it
                        }
                    }

                    return result
                }
            }

            val root = Trie()

            words.forEach {
                root.add(it, 0)
            }

            val base = root.size
            return words.map {
                root.sum(it, 0) - base
            }.toIntArray()
        }
    }

    expect {
        Solution().sumPrefixScores(
            arrayOf("abc", "ab", "bc", "b")
        ).toList()
    }
}