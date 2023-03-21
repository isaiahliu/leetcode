package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reverseStr(s: String, k: Int): String {
            if (k == 1) {
                return s
            }

            val str = StringBuilder()

            var groupStart = 0
            var groupSize = 0
            var size = 0
            var reverse = true
            var readIndex = s.length.coerceAtMost(k) - 1

            while (size < s.length) {
                if (groupSize == k) {
                    groupStart += k
                    groupSize = 0
                    reverse = !reverse

                    readIndex = if (reverse) {
                        s.length.coerceAtMost(groupStart + k) - 1
                    } else {
                        groupStart
                    }
                }

                str.append(s[readIndex])
                size++
                groupSize++

                if (reverse) {
                    readIndex--
                } else {
                    readIndex++
                }
            }

            return str.toString()
        }
    }

    measureTimeMillis {
        Solution().reverseStr(
            "abcdefg", 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}