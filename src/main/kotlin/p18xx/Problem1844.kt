package p18xx

import util.expect

fun main() {
    class Solution {
        fun replaceDigits(s: String): String {
            return s.fold(StringBuilder()) { str, c ->
                str.append(
                    when (c) {
                        in 'a'..'z' -> c
                        else -> str.last() + (c - '0')
                    }
                )
            }.toString()
        }
    }

    expect {
        Solution().replaceDigits(
            "a1c1e1"
        )

    }
}
