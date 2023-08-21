package p03xx

import util.expect

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
                    arrayOf(s.requiredStrAt(it, true), s.requiredStrAt(it, false)).filterNotNull()
                        .filter { it.first != s }.forEach { (target, left) ->
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

    expect {
        Solution().palindromePairs(
            arrayOf("a", "")
        )
    }
}

