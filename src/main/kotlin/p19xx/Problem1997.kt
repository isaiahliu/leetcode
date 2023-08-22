package p19xx

import util.expect

fun main() {
    class Solution {
        fun firstDayBeenInAllRooms(nextVisit: IntArray): Int {
            val m = 1000000007L
            val dp = LongArray(nextVisit.size)

            dp[0] = 1L

            for (i in 1 until dp.size) {
                dp[i] += dp[i - 1] * 2 - dp[nextVisit[i - 1]] + 2
                dp[i] %= m
            }

            return ((dp[dp.lastIndex] - 1 + m) % m).toInt()
        }
    }

    expect {
        Solution().firstDayBeenInAllRooms(
            intArrayOf(0, 0)
        )
    }
}