package p07xx

import util.expect

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

    expect {
        Solution().numMatchingSubseq(
            "", arrayOf()
        )
    }
}