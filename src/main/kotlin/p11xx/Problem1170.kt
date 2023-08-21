package p11xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun numSmallerByFrequency(queries: Array<String>, words: Array<String>): IntArray {
            fun String.f(): Int {
                var min = this[0]
                var count = 0
                forEach {
                    when {
                        it < min -> {
                            min = it
                            count = 1
                        }

                        it == min -> {
                            count++
                        }
                    }
                }

                return count
            }

            val dic = TreeMap<Int, Int>()
            words.map { it.f() }.sortedDescending().forEachIndexed { index, i ->
                dic[i] = index + 1
            }

            return queries.map {
                dic.higherEntry(it.f())?.value ?: 0
            }.toIntArray()
        }
    }

    expect {
        Solution().numSmallerByFrequency(
            arrayOf("bba", "abaaaaaa", "aaaaaa", "bbabbabaab", "aba", "aa", "baab", "bbbbbb", "aab", "bbabbaabb"),
            arrayOf("aaabbb", "aab", "babbab", "babbbb", "b", "bbbbbbbbab", "a", "bbbbbbbbbb", "baaabbaab", "aa")
        ).toList()
    }
}