package p26xx

import util.expect

fun main() {
    class Solution {
        fun addMinimum(word: String): Int {
            var result = 0
            "c${word}d".reduce { acc, c ->
                result += (c - acc - 1).mod(3)
                c
            }

            return result
        }
    }

    expect {
        Solution().addMinimum(
            "c"
        )
    }
}