package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lengthLongestPath(input: String): Int {
            var result = 0

            val lengths = arrayListOf(0)

            var depth = 0
            var name = ""
            (input + "\n").forEach {
                if (it in 'a'..'z' || it in '0'..'9' || it == '.') {
                    name += it
                } else {
                    when (it) {
                        '\n' -> {
                            if ('.' in name) {
                                result = result.coerceAtLeast(lengths[depth] + name.length)
                            } else {
                                (depth + 1).also {
                                    val l = lengths[depth] + name.length + 1
                                    if (it < lengths.size) {
                                        lengths[it] = l
                                    } else {
                                        lengths.add(l)
                                    }
                                }
                            }

                            depth = 0
                            name = ""
                        }

                        '\t' -> {
                            depth++
                        }

                        else -> {
                            name += it
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().lengthLongestPath(
            "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

