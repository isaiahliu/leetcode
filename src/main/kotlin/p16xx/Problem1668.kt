package p16xx

import util.expect

fun main() {
    class Solution {
        fun maxRepeating(sequence: String, word: String): Int {
            var result = 0

            while (word.repeat(result + 1) in sequence) {
                result++
            }

            return result
        }
    }

    expect {
        Solution().maxRepeating(
            "", ""
        )
    }
}

