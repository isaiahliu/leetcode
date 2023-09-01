package p22xx

import util.expect

fun main() {
    class Trie(val valueIndices: Map<String, Set<Int>>) {
        val children = hashMapOf<Int, Trie>()

        var size = 0

        fun add(indices: List<Int>, index: Int) {
            if (index == indices.size) {
                size++
            } else {
                children.computeIfAbsent(indices[index]) { Trie(valueIndices) }.add(indices, index + 1)
            }
        }

        fun query(str: String, index: Int): Int {
            if (index == str.length) {
                return size
            }

            val sub = str.substring(index..index + 1)

            return valueIndices[sub]?.sumOf {
                children[it]?.query(str, index + 2) ?: 0
            } ?: 0
        }
    }

    class Encrypter(keys: CharArray, val values: Array<String>, dictionary: Array<String>) {
        val keyIndices = IntArray(26) { -1 }

        val valueIndices = hashMapOf<String, MutableSet<Int>>()

        val root = Trie(valueIndices)

        init {
            keys.forEachIndexed { index, c ->
                keyIndices[c - 'a'] = index
            }

            values.forEachIndexed { index, s ->
                valueIndices.computeIfAbsent(s) { hashSetOf() }.add(index)
            }

            dictionary.forEach {
                root.add(it.map { keyIndices[it - 'a'] }, 0)
            }
        }

        fun encrypt(word1: String): String {
            return word1.toCharArray().joinToString("") {
                values[keyIndices[it - 'a']]
            }
        }

        fun decrypt(word2: String): Int {
            return root.query(word2, 0)
        }
    }

    val encrypter = Encrypter(
        charArrayOf('a', 'b', 'c', 'd'), arrayOf(
            "ei", "zf", "ei", "am"
        ), arrayOf(
            "abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"
        )
    )
    expect {
        encrypter.encrypt("")
    }
}