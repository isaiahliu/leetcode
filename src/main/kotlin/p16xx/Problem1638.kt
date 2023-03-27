package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSubstrings(s: String, t: String): Int {
            val dp1 = Array(s.length + 1) { IntArray(t.length + 1) }
            val dp2 = Array(s.length + 1) { IntArray(t.length + 1) }

            for (si in s.indices) {
                for (ti in t.indices) {
                    if (s[si] == t[ti]) {
                        dp1[si + 1][ti + 1] = dp1[si][ti] + 1
                    }
                }
            }

            for (si in s.indices.reversed()) {
                for (ti in t.indices.reversed()) {
                    if (s[si] == t[ti]) {
                        dp2[si][ti] = dp2[si + 1][ti + 1] + 1
                    }
                }
            }

            var result = 0
            for (si in s.indices) {
                for (ti in t.indices) {
                    if (s[si] != t[ti]) {
                        result += (dp1[si][ti] + 1) * (dp2[si + 1][ti + 1] + 1)
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countSubstrings(
            "ab", "bb"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}