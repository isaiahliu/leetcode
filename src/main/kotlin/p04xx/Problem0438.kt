package p04xx

import util.expect

fun main() {
    class Solution {
        fun findAnagrams(s: String, p: String): List<Int> {
            val result = arrayListOf<Int>()
            val pCharCount = p.toCharArray().distinct().count()

            val pCounts = IntArray(26)
            p.forEach {
                pCounts[it - 'a']++
            }

            var diffCharCount = pCharCount

            val sCounts = IntArray(26)

            for (i in s.indices) {
                s.getOrNull(i - p.length)?.also {
                    sCounts[it - 'a']--

                    if (sCounts[it - 'a'] == pCounts[it - 'a']) {
                        diffCharCount--
                    } else if (sCounts[it - 'a'] == pCounts[it - 'a'] - 1) {
                        diffCharCount++
                    }
                }

                sCounts[s[i] - 'a']++
                if (sCounts[s[i] - 'a'] == pCounts[s[i] - 'a']) {
                    diffCharCount--
                } else if (sCounts[s[i] - 'a'] == pCounts[s[i] - 'a'] + 1) {
                    diffCharCount++
                }

                if (diffCharCount == 0) {
                    result.add(i - p.length + 1)
                }
            }

            return result
        }
    }

    expect {
        Solution().findAnagrams(
            "cbaebabacd", "abc"
        )
    }
}


