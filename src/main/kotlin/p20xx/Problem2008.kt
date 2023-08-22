package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maxTaxiEarnings(n: Int, rides: Array<IntArray>): Long {
            rides.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })

            val dp = TreeMap<Int, Long>()
            dp[0] = 0L

            rides.forEach { (start, end, tip) ->
                val maxEarn = dp.floorEntry(start).value + end - start + tip

                if (dp.floorEntry(end)?.takeIf { it.value >= maxEarn } == null) {
                    while (true) {
                        dp.ceilingEntry(end)?.takeIf { it.value <= maxEarn }?.also {
                            dp.remove(it.key)
                        } ?: break
                    }

                    dp[end] = maxEarn
                }
            }

            return dp.lastEntry().value
        }
    }

    expect {
        Solution().maxTaxiEarnings(
            5, arrayOf(
                intArrayOf(2, 5, 4),
                intArrayOf(1, 5, 1),
            )
        )
    }
}