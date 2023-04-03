package p06xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun judgePoint24(cards: IntArray): Boolean {
            val dp = Array(0b1111) {
                hashSetOf<Double>()
            }

            fun join(set1: Set<Double>?, set2: Set<Double>?): Set<Double> {
                val res = hashSetOf<Double>()

                set1?.forEach { s1 ->
                    set2?.forEach { s2 ->
                        res.add(s1 + s2)
                        res.add(s1 - s2)
                        res.add(s2 - s1)
                        res.add(s1 * s2)
                        res.add(s1 / s2)
                        res.add(s2 / s1)
                    }
                }

                return res
            }

            repeat(0b1111) { num ->
                repeat(4) { d ->
                    if (num and (1 shl d) > 0) {
                        val target = num - (1 shl d)

                        if (target == 0) {
                            dp[num].add(cards[d].toDouble())
                        } else {
                            dp[num].addAll(join(dp[1 shl d], dp[target]))
                        }
                    }
                }
            }

            return arrayOf(
                0b0001 to 0b1110,
                0b0010 to 0b1101,
                0b0100 to 0b1011,
                0b1000 to 0b0111,
                0b0011 to 0b1100,
                0b0101 to 0b1010,
                0b1001 to 0b0110
            ).any { (l, r) ->
                join(dp[l], dp[r]).any {
                    (it - 24.0).absoluteValue < 0.000001
                }
            }
        }
    }

    measureTimeMillis {
        Solution().judgePoint24(intArrayOf(4, 1, 8, 7)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}