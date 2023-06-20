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

            for (status in 1 until dp[0].size) {
                dp[0][status] = 0
                status.forEachBit {
                    dp[0][status] += cost[0][it]
                }
            }

            dp.reduceIndexed { index, pre, current ->
                for (status in 1 until current.size) {
                    status.forEachBit {
                        val p = 1 shl it

                        current[status] = current[status].coerceAtMost(
                            cost[index][it] + minOf(current[status - p], pre[status - p], pre[status])
                        )
                    }
                }

                current
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


