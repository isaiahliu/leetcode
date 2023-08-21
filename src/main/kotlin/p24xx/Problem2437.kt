package p24xx

import util.expect

fun main() {
    class Solution {
        fun countTime(time: String): Int {
            val (h1, h2, _, m1, m2) = time.toCharArray()

            return when {
                h1 == '?' -> {
                    (0..2).map { countTime("${it}${h2}:${m1}${m2}") }.sum()
                }

                h2 == '?' -> {
                    (0..9).map { countTime("${h1}${it}:${m1}${m2}") }.sum()
                }

                m1 == '?' -> {
                    (0..5).map { countTime("${h1}${h2}:${it}${m2}") }.sum()
                }

                m2 == '?' -> {
                    (0..9).map { countTime("${h1}${h2}:${m1}${it}") }.sum()
                }

                "${h1}${h2}".toInt() < 24 && "${m1}${m2}".toInt() < 60 -> 1
                else -> 0
            }
        }
    }

    expect {
        Solution().countTime(
            ""
        )
    }
}