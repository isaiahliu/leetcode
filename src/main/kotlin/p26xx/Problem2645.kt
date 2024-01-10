package p26xx

import util.expect

fun main() {
    class Solution {
        fun addMinimum(word: String): Int {
            var prev = 'c'
            var result = 0
            word.forEach {
                result += (it - prev - 1).mod(3)

                prev = it
            }

            result += 'c' - prev

            return result
        }
    }

    expect {
        Solution().addMinimum(
            "aaa"
        )
    }
}