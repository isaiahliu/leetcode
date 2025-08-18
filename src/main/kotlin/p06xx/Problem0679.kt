package p06xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun judgePoint24(cards: IntArray): Boolean {
            val dp = Array(1 shl 4) { hashSetOf<Double>() }
            repeat(4) {
                dp[1 shl it] += cards[it].toDouble()
            }

            repeat(16) {
                for (p in 1..it / 2) {
                    if (p and it == p) {
                        dp[p].forEach { s1 ->
                            dp[it - p].forEach { s2 ->
                                dp[it].add(s1 + s2)
                                dp[it].add(s1 - s2)
                                dp[it].add(s2 - s1)
                                dp[it].add(s1 * s2)
                                dp[it].add(s1 / s2)
                                dp[it].add(s2 / s1)
                            }
                        }
                    }
                }
            }

            return dp[0b1111].any { (it - 24).absoluteValue < .0000001 }
        }
    }

    expect {
        Solution().judgePoint24(intArrayOf(4, 1, 8, 7))
    }
}