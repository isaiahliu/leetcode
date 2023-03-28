package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkRecord(n: Int): Int {
            val m = 1000000007

            var cur = arrayOf(
                longArrayOf(1, 1, 0), //无缺席，最近迟到
                longArrayOf(1, 0, 0), //缺席1次，最近迟到
            )

            for (i in 1 until n) {
                cur = arrayOf(
                    longArrayOf((cur[0][0] + cur[0][1] + cur[0][2]) % m, cur[0][0], cur[0][1]),
                    longArrayOf(
                        (cur[0][0] + cur[0][1] + cur[0][2] + cur[1][0] + cur[1][1] + cur[1][2]) % m,
                        cur[1][0], cur[1][1]
                    ),
                )
            }

            return (cur.map { it.sum() }.sum() % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().checkRecord(
            10101
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}