package p02xx

import util.expect

fun main() {
    class Solution {
        fun isAnagram(s: String, t: String): Boolean {
            if (s.length != t.length) {
                return false
            }

            val counts = IntArray(26)
            var diffCharCounts = 0

            s.forEach {
                (counts[it - 'a']++).also {
                    if (it == 0) {
                        diffCharCounts++
                    }
                }
            }

            t.forEach {
                (counts[it - 'a']--).also {
                    if (it == 1) {
                        diffCharCounts--
                    }
                }
            }

            return diffCharCounts == 0
        }
    }

    expect {
        Solution().isAnagram(
            "anagram",
            "nagaram"
        )
    }
}

