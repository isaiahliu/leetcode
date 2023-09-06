package p23xx

import util.expect

fun main() {
    class Solution {
        fun greatestLetter(s: String): String {
            val set = s.toSet()

            (25 downTo 0).forEach {
                if ('A' + it in set && 'a' + it in set) {
                    return ('A' + it).toString()
                }
            }

            return ""
        }
    }

    expect {
        Solution().greatestLetter(
            ""
        )
    }
}