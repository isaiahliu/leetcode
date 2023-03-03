package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun palindromePairs(words: Array<String>): List<List<Int>> {
            val wordIndices = words.mapIndexed { index, s -> s to index }.toMap()

            val result = arrayListOf<List<Int>>()

            fun String.requiredStrAt(index: Int, includeChar: Boolean): Pair<String, Boolean>? {
                var leftIndex = index
                var rightIndex = index + 1

                if (!includeChar) {
                    leftIndex--
                }

                while (leftIndex >= 0 && rightIndex < this.length) {
                    if (this[leftIndex--] != this[rightIndex++]) {
                        return null
                    }
                }

                return if (leftIndex >= 0) {
                    this.substring(0, leftIndex + 1).reversed() to false
                } else {
                    this.substring(rightIndex).reversed() to true
                }
            }

            words.forEachIndexed { index, s ->
                repeat(s.length) {
                    s.requiredStrAt(it, true)?.takeIf { it.first != s }?.also { (target, left) ->
                        wordIndices[target]?.also {
                            if (target.isEmpty()) {
                                result.add(listOf(it, index))
                                result.add(listOf(index, it))
                            } else {
                                result.add(
                                    if (left) {
                                        listOf(it, index)
                                    } else {
                                        listOf(index, it)
                                    }
                                )
                            }
                        }
                    }

                    s.requiredStrAt(it, false)?.takeIf { it.first != s }?.also { (target, left) ->
                        wordIndices[target]?.also {
                            if (target.isEmpty()) {
                                result.add(listOf(it, index))
                                result.add(listOf(index, it))
                            } else {
                                result.add(
                                    if (left) {
                                        listOf(it, index)
                                    } else {
                                        listOf(index, it)
                                    }
                                )
                            }
                        }
                    }

                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().palindromePairs(
            arrayOf("a", "")
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

