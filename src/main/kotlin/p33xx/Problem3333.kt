package p33xx

import util.expect

fun main() {
    class Solution {
        fun possibleStringCount(word: String, k: Int): Int {
            val m = 1000000007
            var count = 1
            val freq = arrayListOf<Int>()
            var result = 1L
            for (i in 1 until word.length) {
                if (word[i] == word[i - 1]) {
                    count++
                } else {
                    freq += count
                    result *= count
                    result %= m
                    count = 1
                }
            }
            freq += count
            result *= count
            result %= m

            if (freq.size >= k) {
                return result.toInt()
            }

            var f = IntArray(k)
            f[0] = 1
            var g = IntArray(k) { 1 }
            for (fr in freq) {
                val newF = IntArray(k)
                for (j in 1..<k) {
                    newF[j] = g[j - 1]
                    if (j - fr - 1 >= 0) {
                        newF[j] = (newF[j] - g[j - fr - 1] + m) % m
                    }
                }
                val newG = IntArray(k)
                newG[0] = newF[0]
                for (j in 1..<k) {
                    newG[j] = (newG[j - 1] + newF[j]) % m
                }
                f = newF
                g = newG
            }

            return ((result - g[k - 1] + m) % m).toInt()
        }
    }

    expect {
        Solution().possibleStringCount(
            "aabbccdd", 7
        )
    }
}
