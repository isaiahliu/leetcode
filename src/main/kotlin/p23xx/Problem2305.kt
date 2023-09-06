package p23xx

import util.expect

fun main() {
    class Solution {
        fun distributeCookies(cookies: IntArray, k: Int): Int {
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

            val dp = Array(k) { IntArray(1 shl cookies.size) { Int.MAX_VALUE } }

            dp.forEachIndexed { index, d ->
                repeat(1 shl cookies.size) { status ->
                    if (index == 0) {
                        var sum = 0
                        status.forEachBit {
                            sum += cookies[it]
                        }

                        d[status] = sum
                    } else {
                        val halfCount = (index + 1) / 2
                        repeat(1 shl cookies.size) { subStatus ->
                            if (subStatus and status == subStatus) {
                                for (left in 0 until halfCount) {
                                    d[status] =
                                        d[status].coerceAtMost(dp[left][subStatus].coerceAtLeast(dp[index - left - 1][status - subStatus]))
                                }
                            }
                        }
                    }
                }
            }

            return dp.last().last()
        }
    }

    expect {
        Solution().distributeCookies(
            intArrayOf(8, 15, 10, 20, 8), 2
        )
    }
}