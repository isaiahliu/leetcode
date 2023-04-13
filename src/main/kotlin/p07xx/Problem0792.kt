package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numMatchingSubseq(s: String, words: Array<String>): Int {
            return words.count {
                var l = 0
                var r = 0

                while (l < s.length && r < it.length) {
                    if (s[l++] == it[r]) {
                        r++
                    }
                }

                r == it.length
            }
        }
    }

    measureTimeMillis {
        Solution().numMatchingSubseq(
            "", arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}