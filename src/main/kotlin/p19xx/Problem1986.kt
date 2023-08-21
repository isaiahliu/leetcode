package p19xx

import util.expect

fun main() {
    class Solution {
        fun minSessions(tasks: IntArray, sessionTime: Int): Int {
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

            val dp = IntArray(1 shl tasks.size) { tasks.size }
            dp[0] = 0

            repeat(dp.size) { status ->
                var sum = 0
                status.forEachBit {
                    sum += tasks[it]
                }

                when {
                    sum == 0 -> {
                    }

                    sum <= sessionTime -> {
                        dp[status] = 1
                    }

                    else -> {
                        repeat(status) {
                            if (it > 0 && status and it == it) {
                                dp[status] = dp[status].coerceAtMost(dp[it] + dp[status - it])
                            }
                        }
                    }
                }
            }

            return dp[dp.lastIndex]
        }
    }

    expect {
        Solution().minSessions(
            intArrayOf(2, 10, 1, 10, 4, 4, 7, 10, 7, 4, 10, 2), 15
        )
    }
}