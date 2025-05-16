package p29xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun getWordsInLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
            val lengthGroups = hashMapOf<Int, MutableList<Int>>()

            val prevIndices = arrayOfNulls<Int>(words.size)
            val prevLengths = IntArray(words.size)

            words.forEachIndexed { index, word ->
                lengthGroups[word.length]?.forEach { prevIndex ->
                    if (groups[index] != groups[prevIndex] && word.indices.sumOf {
                            word[it].compareTo(words[prevIndex][it]).absoluteValue
                        } == 1) {
                        val length = prevLengths[prevIndex] + 1
                        if (prevLengths[index] < length) {
                            prevLengths[index] = length
                            prevIndices[index] = prevIndex
                        }
                    }
                }

                lengthGroups.computeIfAbsent(word.length) { arrayListOf() } += index
            }

            return words.indices.maxBy { prevLengths[it] }.let {
                buildList {
                    var t: Int? = it
                    while (t != null) {
                        add(0, words[t])
                        t = prevIndices[t]
                    }
                }
            }
        }
    }

    expect {
        Solution().getWordsInLongestSubsequence(
            arrayOf("bdb", "aaa", "ada"), intArrayOf(2, 1, 3)
        )
    }
}
