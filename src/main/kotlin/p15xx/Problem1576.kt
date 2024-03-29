package p15xx

import util.expect

fun main() {
    class Solution {
        fun modifyString(s: String): String {
            val chars = s.toCharArray()

            s.forEachIndexed { index, c ->
                if (c == '?') {
                    var char = 'a' + (chars.getOrNull(index - 1)?.let { (it - 'a' + 1) % 26 } ?: 0)
                    if (char == chars.getOrNull(index + 1)) {
                        char = 'a' + (char - 'a' + 1) % 26
                    }

                    chars[index] = char
                }
            }

            return String(chars)
        }
    }

    expect {
        Solution().modifyString(
            "?zw"
        )
    }
}

