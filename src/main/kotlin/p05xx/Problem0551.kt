package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkRecord(s: String): Boolean {
            var absentTimes = 0
            var lateTimes = 0
            for (c in s) {
                when (c) {
                    'A' -> {
                        lateTimes = 0
                        if (++absentTimes == 2) {
                            return false
                        }
                    }

                    'L' -> {
                        if (++lateTimes == 3) {
                            return false
                        }
                    }

                    'P' -> {
                        lateTimes = 0
                    }
                }

            }
            return true
        }
    }

    measureTimeMillis {
        Solution().checkRecord(
            ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}