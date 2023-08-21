package p11xx

import util.expect

fun main() {
    class Solution {
        fun countCharacters(words: Array<String>, chars: String): Int {
            val dic = chars.groupingBy { it }.eachCount()

            var result = 0
            words.forEach {
                if (it.groupingBy { it }.eachCount().all { (c, count) -> (dic[c] ?: 0) >= count }) {
                    result += it.length
                }
            }

            return result
        }
    }

    expect {
        Solution().countCharacters(
            arrayOf(), ""
        )
    }
}