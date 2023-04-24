package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun decodeAtIndex(s: String, k: Int): String {
            class Node(var str: String) {
                var parent: Node? = null
                var size: Long = 0

                fun search(index: Int): String {
                    return str.getOrNull(str.length - (size - index).toInt())?.toString() ?: parent?.let {
                        it.search((index.toLong() % it.size).toInt())
                    }.orEmpty()
                }
            }

            var root = Node("")
            loop@ for (c in s) {
                when (c) {
                    in 'a'..'z' -> {
                        root.str += c
                        root.size++
                    }

                    else -> {
                        root = Node("").also {
                            it.parent = root
                            it.size = root.size * (c - '0')
                        }

                        if (root.size >= k) {
                            break@loop
                        }
                    }
                }
            }

            return root.search(k - 1)
        }
    }

    measureTimeMillis {
        Solution().decodeAtIndex(
            "ha22", 5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}