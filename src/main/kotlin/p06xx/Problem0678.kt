package p06xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkValidString(s: String): Boolean {
            val dp = Array(s.length + 1) { hashSetOf<Int>() }
            dp[0].add(0)

            s.forEachIndexed { index, c ->
                dp[index + 1].addAll(
                    when (c) {
                        '(' -> {
                            dp[index].map { it + 1 }
                        }

                        ')' -> {
                            dp[index].map { it - 1 }.filter { it >= 0 }
                        }

                        else -> {
                            dp[index].map { it + 1 } + dp[index].map { it } + dp[index].map { it - 1 }
                                .filter { it >= 0 }
                        }
                    }
                )
            }

            return 0 in dp[dp.lastIndex]
        }
    }

    measureTimeMillis {
        Solution().checkValidString("").also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}