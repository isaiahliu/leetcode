package p28xx

import util.expect

fun main() {
    class Solution {
        fun isAcronym(words: List<String>, s: String): Boolean {
            if (words.size != s.length) {
                return false
            }

            s.forEachIndexed { index, c ->
                if (words[index][0] != c) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isAcronym(
            listOf(""), ""
        )
    }
}
