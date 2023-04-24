package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lastSubstring(s: String): String {
            var rootIndex = 0
            var newRootIndex = 0
            var checkIndex = 0

            var hasLower = false
            for (i in 1 until s.length) {
                val c = s[i]

                if (c > s[rootIndex]) {
                    rootIndex = i
                    newRootIndex = 0
                    checkIndex = 0
                    hasLower = false
                }

                if (newRootIndex > 0 && checkIndex > 0) {
                    if (c < s[rootIndex]) {
                        hasLower = true
                    }

                    if (c < s[checkIndex]) {
                        checkIndex = 0
                        newRootIndex = 0
                    } else if (c > s[checkIndex]) {
                        rootIndex = newRootIndex
                        newRootIndex = 0
                        checkIndex = 0

                        if (c == s[rootIndex]) {
                            newRootIndex = i
                            checkIndex = rootIndex + 1
                        }
                    }
                }
                if (c == s[rootIndex] && (newRootIndex == 0 || hasLower)) {
                    newRootIndex = i
                    checkIndex = rootIndex + 1
                    hasLower = false
                } else {
                    if (checkIndex > 0) {
                        checkIndex++
                    }
                }

            }

            return s.substring(rootIndex)
        }
    }

    measureTimeMillis {
        Solution().lastSubstring(
            "zaazaabzaazb"
        ).also { println(it) }
        Solution().lastSubstring(
            "cacacb"
        ).also { println(it) }
        Solution().lastSubstring(
            "zzxzzzx"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}