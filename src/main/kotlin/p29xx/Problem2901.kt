package p29xx

import util.expect
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    class Solution {
        fun getWordsInLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
            val lengthGroups = hashMapOf<Int, MutableList<Int>>()

            val prevs = arrayOfNulls<Int>(words.size)
            val prevLengths = IntArray(words.size)

            words.forEachIndexed { index, word ->
                lengthGroups[word.length]?.forEach {
                    val target = words[it]

                    if (groups[index] != groups[it] && word.indices.sumOf {
                            (word[it] - target[it]).sign.absoluteValue
                        } == 1) {
                        val length = prevLengths[it] + 1
                        if (prevLengths[index] < length) {
                            prevLengths[index] = length
                            prevs[index] = it
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
                        t = prevs[t]
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
