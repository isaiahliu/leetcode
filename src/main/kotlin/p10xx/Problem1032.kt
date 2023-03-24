package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class DicNode {
        var match = false

        val children = arrayOfNulls<DicNode>(26)

        fun fill(str: String) {
            str.firstOrNull()?.also { c ->
                val child = children.getOrNull(c - 'a') ?: run {
                    DicNode().also { children[c - 'a'] = it }
                }

                child.fill(str.substring(1))
            } ?: run {
                match = true
            }
        }
    }

    class StreamChecker(words: Array<String>) {
        val root = DicNode()

        val dics = arrayListOf<DicNode>()

        init {
            words.forEach { root.fill(it) }
        }

        fun query(letter: Char): Boolean {
            val charIndex = letter - 'a'

            val current = dics.toList() + root
            dics.clear()

            var result = false
            current.forEach {
                it.children.getOrNull(charIndex)?.also {
                    result = result || it.match
                    dics.add(it)
                }
            }

            return result
        }
    }

    measureTimeMillis {
        val sol = StreamChecker(arrayOf("abc", "xyz"))
        sol.query('a').also { println(it) }
        sol.query('x').also { println(it) }
        sol.query('y').also { println(it) }
        sol.query('z').also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}