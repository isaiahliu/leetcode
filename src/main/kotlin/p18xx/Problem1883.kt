package p18xx

import util.expect

fun main() {
    class Solution {
        fun minSkips(dist: IntArray, speed: Int, hoursBefore: Int): Int {
            fun Pair<Int, Int>.min(right: Pair<Int, Int>?): Pair<Int, Int> {
                return right?.takeIf { right.first < first || right.first == first && right.second < second } ?: this
            }

            fun Pair<Int, Int>.valid(): Pair<Int, Int>? {
                return this.takeIf { (hour, rem) -> hour < hoursBefore || hour == hoursBefore && rem == 0 }
            }

            val dp = hashMapOf(0 to (0 to 0))

            for (road in dist) {
                dp.toMap().also { dp.clear() }.forEach { (skipCount, cost) ->
                    var (hour, rem) = cost

                    rem += road
                    hour += rem / speed
                    rem %= speed

                    if (rem > 0) {
                        (hour to rem).min(dp[skipCount + 1]).valid()?.also {
                            dp[skipCount + 1] = it
                        }

                        (hour + 1 to 0).min(dp[skipCount]).valid()?.also {
                            dp[skipCount] = it
                        }
                    } else {
                        (hour to 0).min(dp[skipCount]).valid()?.also {
                            dp[skipCount] = it
                        }
                    }
                }

                if (dp.isEmpty()) {
                    return -1
                }
            }

            return dp.keys.min()
        }
    }

    expect {
        Solution().minSkips(
            intArrayOf(1, 3, 2), 4, 2
        )
    }
}
