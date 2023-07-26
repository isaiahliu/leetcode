package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkIfCanBreak(s1: String, s2: String): Boolean {
            val sorted1 = s1.toCharArray().also { it.sort() }
            val sorted2 = s2.toCharArray().also { it.sort() }

            var eq = 0

            repeat(s1.length) {
                if (sorted1[it] < sorted2[it]) {
                    if (eq > 0) {
                        return false
                    }
                    eq = -1
                } else if (sorted1[it] > sorted2[it]) {
                    if (eq < 0) {
                        return false
                    }
                    eq = 1
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().checkIfCanBreak(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

