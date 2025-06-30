package p33xx

import util.expect

fun main() {
    class Solution {
        fun possibleStringCount(word: String): Int {
            var result = 1

            var pre = word[0] - 1
            word.forEach {
                when (it) {
                    pre -> {
                        result++
                    }

                    else -> {
                        pre = it
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().possibleStringCount(
            ""
        )
    }
}
