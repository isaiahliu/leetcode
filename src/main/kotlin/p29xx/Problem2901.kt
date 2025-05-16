package p29xx

import util.expect
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun getWordsInLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
            val prevIndices = arrayOfNulls<Int>(words.size)

            val lengthGroups = hashMapOf<Int, TreeMap<Int, MutableSet<Int>>>()

            var result: Int? = 0
            var maxLength = 0

            words.forEachIndexed { index, word ->
                val prevLengths = lengthGroups.computeIfAbsent(word.length) { TreeMap(compareByDescending { it }) }

                for ((length, indices) in prevLengths) {
                    indices.forEach { prevIndex ->
                        if (groups[index] != groups[prevIndex] && word.indices.sumOf {
                                word[it].compareTo(words[prevIndex][it]).absoluteValue
                            } == 1) {
                            if (length + 1 > maxLength) {
                                maxLength = length + 1
                                result = index
                            }
                            prevIndices[index] = prevIndex
                            prevLengths.computeIfAbsent(length + 1) { hashSetOf() } += index

                            return@forEachIndexed
                        }
                    }
                }

                prevLengths.computeIfAbsent(0) { hashSetOf() } += index
            }

            return buildList {
                while (result != null) {
                    add(0, words[result])
                    result = prevIndices[result]
                }

            }
        }
    }

    expect {
        Solution().getWordsInLongestSubsequence(
            arrayOf("bad", "dc", "bc", "ccd", "dd", "da", "cad", "dba", "aba"), intArrayOf(9, 7, 1, 2, 6, 8, 3, 7, 2)
        )
    }
}
