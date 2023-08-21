package p02xx

import util.expect

fun main() {
    class Node {
        var match: Boolean = false

        val children = arrayOfNulls<Node>(26)

        operator fun Array<Node?>.get(char: Char): Node? {
            return this[char - 'a']
        }

        operator fun Array<Node?>.set(char: Char, node: Node?) {
            this[char - 'a'] = node
        }

        fun insert(word: String, index: Int = 0) {
            if (index == word.length) {
                match = true
            } else {
                val child = children[word[index]] ?: run {
                    Node().also { children[word[index]] = it }
                }

                child.insert(word, index + 1)
            }
        }

        fun search(word: String, index: Int = 0): Boolean {
            return if (index == word.length) {
                match
            } else {
                val c = word[index]
                if (c == '.') {
                    children.any { it?.search(word, index + 1) ?: false }
                } else {
                    children[word[index]]?.search(word, index + 1) ?: false
                }
            }
        }
    }

    class WordDictionary {
        val root = Node()

        fun addWord(word: String) {
            root.insert(word)
        }

        fun search(word: String): Boolean {
            return root.search(word)
        }
    }

    expect {
        WordDictionary().addWord("")
    }
}

