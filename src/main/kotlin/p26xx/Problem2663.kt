package p26xx

import util.expect

fun main() {
    class Solution {
        fun smallestBeautifulString(s: String, k: Int): String {
            val result = CharArray(s.length)

            fun dfs(index: Int, sameBefore: Boolean): Boolean {
                if (index == s.length) {
                    return !sameBefore
                }
                val start = if (sameBefore) s[index] else 'a'

                for (c in start until 'a' + k) {
                    if (c != result.getOrNull(index - 1) && c != result.getOrNull(index - 2)) {
                        result[index] = c

                        if (dfs(index + 1, sameBefore && c == start)) {
                            return true
                        }
                    }
                }

                return false
            }

            return if (dfs(0, true)) {
                result.concatToString()
            } else {
                ""
            }
        }
    }

    expect {
        Solution().smallestBeautifulString(
            "abcz", 26
        )
    }
}
