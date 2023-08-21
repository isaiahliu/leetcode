package p10xx

import util.expect

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
        val root = DicNode().also {
            words.forEach { word -> it.fill(word) }
        }

        var dics = listOf<DicNode>()

        fun query(letter: Char): Boolean {
            dics = (dics + root).mapNotNull { it.children.getOrNull(letter - 'a') }

            return dics.any { it.match }
        }
    }

    expect {
        val sol = StreamChecker(arrayOf("abc", "xyz"))
        sol.query('a')
        sol.query('x')
        sol.query('y')
        sol.query('z')
    }
}