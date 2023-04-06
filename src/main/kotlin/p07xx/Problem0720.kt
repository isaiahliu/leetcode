package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestWord(words: Array<String>): String {
            class Trie {
                val children = arrayOfNulls<Trie>(26)

                var match = false

                fun add(word: String, index: Int = 0, matchPre: Boolean = true): Boolean {
                    return if (index == word.length) {
                        match = true

                        matchPre
                    } else {
                        (children[word[index] - 'a'] ?: run {
                            Trie().also { children[word[index] - 'a'] = it }
                        }).add(word, index + 1, matchPre && match)
                    }
                }
            }

            val root = Trie()
            root.match = true

            var result = ""
            words.sortedBy { it.length }.forEach {
                if (root.add(it) && (it.length > result.length || it < result)) {
                    result = it
                }
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().longestWord(
            arrayOf("w", "wo", "wor", "worl", "world")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}