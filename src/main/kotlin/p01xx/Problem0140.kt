package p01xx

import util.expect

fun main() {
    class Solution {
        fun wordBreak(s: String, wordDict: List<String>): List<String> {
            val dp = Array(s.length + 1) { arrayListOf<String>() }
            dp[0].add("")

            val lengths = wordDict.map { it.length }.distinct().sorted()

            for (index in lengths[0] until dp.size) {
                lengths.filter { index - it >= 0 }.forEach { l ->
                    val subWord = s.substring(index - l, index)
                    if (subWord in wordDict) {
                        dp[index - l].forEach { p ->
                            dp[index].add("${p}${if (p.isEmpty()) "" else " "}${subWord}")
                        }
                    }
                }
            }

            return dp[s.length]
        }
    }

    expect {
        Solution().wordBreak(
            "catsanddog", listOf("cats", "dog", "sand", "and", "cat")
        )
    }
}

