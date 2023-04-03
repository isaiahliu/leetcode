package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Trie {
        var match = false

        val children = arrayOfNulls<Trie>(26)

        fun fill(word: String, index: Int = 0) {
            word.getOrNull(index)?.also { c ->
                val child = children[c - 'a'] ?: Trie().also {
                    children[c - 'a'] = it
                }

                child.fill(word, index + 1)
            } ?: run {
                match = true
            }
        }

        fun search(word: String, index: Int = 0, skippedError: Boolean = false): Boolean {
            return when {
                index == word.length -> match && skippedError
                skippedError -> children[word[index] - 'a']?.search(word, index + 1, true) ?: false
                else -> {
                    var found = children[word[index] - 'a']?.search(word, index + 1, false) ?: false

                    if (!found) {
                        for ((i, trie) in children.withIndex()) {
                            if (trie?.takeIf { i != word[index] - 'a' }?.search(word, index + 1, true) == true) {
                                found = true
                                break
                            }
                        }
                    }

                    found
                }
            }
        }
    }

    class MagicDictionary {
        val root = Trie()

        fun buildDict(dictionary: Array<String>) {
            dictionary.forEach {
                root.fill(it)
            }
        }

        fun search(searchWord: String): Boolean {
            return root.search(searchWord)
        }
    }


    measureTimeMillis {
        val dic = MagicDictionary()
        dic.buildDict(arrayOf("ha"))

        dic.search("ha").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}