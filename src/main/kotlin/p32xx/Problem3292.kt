package p32xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun minValidStrings(words: Array<String>, target: String): Int {
            val back = IntArray(target.length)
            for (word in words) {
                val pi = prefixFunction(word, target)
                val m = word.length
                for (i in target.indices) {
                    back[i] = maxOf(back[i], pi[m + 1 + i])
                }
            }
            val dp = IntArray(target.length + 1) {
                it.sign * target.length
            }
            for (i in target.indices) {
                dp[i + 1] = dp[i + 1 - back[i]] + 1

                if (dp[i + 1] > target.length) {
                    return -1
                }
            }
            return dp.last()
        }

        fun prefixFunction(word: String, target: String): IntArray {
            val s = "$word#$target"
            val pi = IntArray(s.length)
            for (i in 1 until s.length) {
                var j = pi[i - 1]
                while (j > 0 && s[i] != s[j]) {
                    j = pi[j - 1]
                }

                if (s[i] == s[j]) {
                    j++
                }
                pi[i] = j
            }
            return pi
        }
    }

    expect {
        Solution().minValidStrings(
            arrayOf(
                "abc", "aaaaa", "bcdef"
            ), "aabcdabc"
        )
    }
}
