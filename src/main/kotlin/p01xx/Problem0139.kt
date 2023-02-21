package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun wordBreak(s: String, wordDict: List<String>): Boolean {
            val dp = BooleanArray(s.length + 1)
            dp[0] = true

            val lengths = wordDict.map { it.length }.distinct().sorted()

            for (index in lengths[0] until dp.size) {
                dp[index] = lengths.any { l ->
                    index - l >= 0 && dp[index - l] && s.substring(index - l, index) in wordDict
                }
            }

            return dp[s.length]
        }
    }

    measureTimeMillis {
        Solution().wordBreak(
            "catsanddog", listOf("cats", "dog", "sand", "and", "cat")
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

