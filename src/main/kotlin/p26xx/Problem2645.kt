package p26xx

import util.expect

fun main() {
    class Solution {
        fun addMinimum(word: String): Int {
            return "${word}a".fold('c' to 0) { (prev, result), c ->
                c to result + (c - prev - 1).mod(3)
            }.second
        }
    }

    expect {
        Solution().addMinimum(
            "c"
        )
    }
}