package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumLengthEncoding(words: Array<String>): Int {
            class Trie(val length: Int) {
                val children = hashMapOf<Char, Trie>()
            }

            val leaves = hashSetOf<Trie>()

            fun Trie.add(str: String, index: Int) {
                val c = str.getOrNull(index)

                if (c == null) {
                    leaves.add(this)
                    return
                }

                val child = children.computeIfAbsent(c) { Trie(length + 1) }

                child.add(str, index - 1)
            }

            val root = Trie(1)

            words.forEach {
                root.add(it, it.lastIndex)
            }

            return leaves.filter { it.children.isEmpty() }.map { it.length }.sum()
        }
    }

    measureTimeMillis {
        Solution().minimumLengthEncoding(
            arrayOf("time")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}