package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Trie {
        var sum = 0

        val children = arrayOfNulls<Trie>(26)

        fun fill(word: String, index: Int, value: Int) {
            sum += value

            val cIndex = (word.getOrNull(index) ?: return) - 'a'
            val child = children[cIndex] ?: Trie().also {
                children[cIndex] = it
            }

            child.fill(word, index + 1, value)
        }

        fun search(word: String, index: Int = 0): Int {
            return when (index) {
                word.length -> sum
                else -> children[word[index] - 'a']?.search(word, index + 1) ?: 0
            }
        }
    }

    class MapSum {
        val map = hashMapOf<String, Int>()

        val root = Trie()

        fun insert(key: String, `val`: Int) {
            val last = map[key] ?: 0

            map[key] = `val`

            root.fill(key, 0, `val` - last)
        }

        fun sum(prefix: String): Int {
            return root.search(prefix)
        }
    }

    measureTimeMillis {
        val dic = MapSum()
        dic.insert("apple", 3)
        dic.sum("ap").also { println(it) }
        dic.insert("app", 2)
        dic.sum("ap").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}