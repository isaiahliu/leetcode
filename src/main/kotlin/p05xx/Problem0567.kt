package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkInclusion(s1: String, s2: String): Boolean {
            if (s2.length < s1.length) {
                return false
            }

            val counts = IntArray(26)

            var c = 0
            fun Char.add(count: Int) {
                val i = this - 'a'

                if (counts[i] == 0) {
                    c++
                }

                counts[i] += count

                if (counts[i] == 0) {
                    c--
                }
            }

            s1.forEach {
                it.add(-1)
            }

            s2.take(s1.length).forEach {
                it.add(1)
            }

            if (c == 0) {
                return true
            }

            var left = 0
            var right = s1.length

            while (right < s2.length) {
                s2[left++].add(-1)
                s2[right++].add(1)

                if (c == 0) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().checkInclusion(
            "abc", "bbbca"
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}