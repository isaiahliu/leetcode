package p22xx

import util.expect

fun main() {
    class Solution {
        fun percentageLetter(s: String, letter: Char): Int {
            return s.count { it == letter } * 100 / s.length
        }
    }

    expect {
        Solution().percentageLetter("", ' ')
    }
}