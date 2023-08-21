package p03xx

import util.expect

fun main() {
    class Solution {
        fun canConstruct(ransomNote: String, magazine: String): Boolean {
            val counts = IntArray(26)

            magazine.forEach {
                counts[it - 'a']++
            }

            for (c in ransomNote) {
                if (counts[c - 'a']-- == 0) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().canConstruct(
            "aa", "aab"
        )
    }
}

