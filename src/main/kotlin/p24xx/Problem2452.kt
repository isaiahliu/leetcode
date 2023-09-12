package p24xx

import util.expect

fun main() {
    class Solution {
        fun twoEditWords(queries: Array<String>, dictionary: Array<String>): List<String> {
            class Trie {
                val children = hashMapOf<Char, Trie>()

                var match = false

                fun add(word: String, index: Int) {
                    if (index == word.length) {
                        match = true
                    } else {
                        children.computeIfAbsent(word[index]) { Trie() }.add(word, index + 1)
                    }
                }

                fun query(word: String, index: Int, modify: Int): Boolean {
                    if (index == word.length) {
                        return match
                    }

                    return children.any { (ch, t) ->
                        when {
                            ch == word[index] -> t.query(word, index + 1, modify)
                            modify > 0 -> t.query(word, index + 1, modify - 1)
                            else -> false
                        }
                    }
                }
            }

            val root = Trie()

            dictionary.forEach {
                root.add(it, 0)
            }

            return queries.filter {
                root.query(it, 0, 2)
            }
        }
    }

    expect {
        Solution().twoEditWords(
            arrayOf(), arrayOf()
        )
    }
}