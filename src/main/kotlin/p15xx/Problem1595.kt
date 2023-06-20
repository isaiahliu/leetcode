package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun connectTwoGroups(cost: List<List<Int>>): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val dp = Array(cost.size) {
                IntArray(1 shl cost[0].size) { Int.MAX_VALUE }
            }

            dp[0].also { dp0 ->
                for (status in 1 until dp0.size) {
                    var sum = 0
                    status.forEachBit {
                        sum += cost[0][it]
                    }

                    dp0[status] = sum
                }
            }

            for (dpIndex in 1 until dp.size) {
                val current = dp[dpIndex]
                val pre = dp[dpIndex - 1]

                for (status in 1 until current.size) {
                    var min = Int.MAX_VALUE
                    status.forEachBit {
                        val p = 1 shl it
                        val num = cost[dpIndex][it]

                        min = min.coerceAtMost(
                            num + pre[status].coerceAtMost(current[status - p]).coerceAtMost(pre[status - p])
                        )
                    }

                    current[status] = min
                }
            }

            return dp.last().last()
        }
    }

    measureTimeMillis {
        Solution().connectTwoGroups(
            listOf(
                listOf(1, 3, 5),
                listOf(4, 1, 1),
                listOf(1, 5, 3),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


