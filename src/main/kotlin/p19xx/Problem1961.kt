package p19xx

import util.expect

fun main() {
    class Solution {
        fun isPrefixString(s: String, words: Array<String>): Boolean {
            var index = 0

            words.forEach {
                it.forEach {
                    if (s.getOrNull(index++) != it) {
                        return false
                    }
                }

                if (index == s.length) {
                    return true
                }
            }

            return false
        }
    }

    expect {
        Solution().isPrefixString(
            "", arrayOf()
        )
    }
}