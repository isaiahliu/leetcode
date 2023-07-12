package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findNumOfValidWords(words: Array<String>, puzzles: Array<String>): List<Int> {
            class DicNode(val c: Char) {
                var count = 0

                var children = arrayOfNulls<DicNode>(2)

                fun init(index: Int, word: CharArray) {
                    when {
                        word[index] != c -> {
                            val child = children[1] ?: run {
                                DicNode(c + 1).also { children[1] = it }
                            }

                            child.init(index, word)
                        }

                        index == word.lastIndex -> {
                            count++
                        }

                        else -> {
                            val child = children[0] ?: run {
                                DicNode(c + 1).also { children[0] = it }
                            }

                            child.init(index + 1, word)
                        }
                    }
                }

                fun match(mandatory: Char, index: Int, puzzle: CharArray): Int {
                    var result = 0

                    when {
                        index > puzzle.lastIndex -> {
                        }

                        puzzle[index] != c -> {
                            children[1]?.also {
                                result += it.match(mandatory, index, puzzle)
                            }
                        }

                        mandatory > c -> {
                            children.forEach {
                                it?.also {
                                    result += it.match(mandatory, index + 1, puzzle)
                                }
                            }
                        }

                        mandatory == c -> {
                            result += count

                            children[0]?.also {
                                result += it.match(mandatory, index + 1, puzzle)
                            }
                        }

                        else -> {
                            result += count

                            children.forEach {
                                it?.also {
                                    result += it.match(mandatory, index + 1, puzzle)
                                }
                            }
                        }
                    }

                    return result
                }
            }

            val root = DicNode('a' - 1)

            words.forEach {
                root.init(0, it.toSortedSet().toCharArray())
            }

            return puzzles.map {
                root.match(it[0], 0, it.toSortedSet().toCharArray())
            }
        }
    }

    measureTimeMillis {
        Solution().findNumOfValidWords(
            arrayOf("az"), arrayOf("a")
        ).also { println(it) }

        Solution().findNumOfValidWords(
            arrayOf("aaaa", "asas", "able", "ability", "actt", "actor", "access"),
            arrayOf("aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}