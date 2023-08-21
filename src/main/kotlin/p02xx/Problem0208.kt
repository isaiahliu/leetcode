package p02xx

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
                children[word[index]]?.search(word, index + 1) ?: false
            }
        }

        fun startsWith(word: String, index: Int = 0): Boolean {
            return if (index == word.length) {
                true
            } else {
                children[word[index]]?.startsWith(word, index + 1) ?: false
            }
        }
    }

    class Trie {
        val root = Node()

        fun insert(word: String) {
            root.insert(word)
        }

        fun search(word: String): Boolean {
            return root.search(word)
        }

        fun startsWith(prefix: String): Boolean {
            return root.startsWith(prefix)
        }
    }

    val trie = Trie()
    trie.insert("apple")
    trie.search("app")
    trie.startsWith("app")
}

