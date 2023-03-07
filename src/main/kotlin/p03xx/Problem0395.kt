package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun longestSubstring(s: String, k: Int): Int {
            if (k == 1) {
                return s.length
            }

            var result = 0
            fun process(subStr: String) {
                if (subStr.length <= result) {
                    return
                }

                for (c in subStr) {
                    if (subStr.count { it == c } < k) {

                        subStr.split(c).forEach {
                            process(it)
                        }

                        return
                    }
                }

                result = subStr.length
            }

            process(s)

            return result
        }
    }

    measureTimeMillis {
        Solution().longestSubstring(
            "abcdedghijklmnopqrstuvwxyz", 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

