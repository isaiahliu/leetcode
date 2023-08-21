package p08xx

import util.expect

fun main() {
    class Solution {
        fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {
            if (stations.isEmpty()) {
                return if (startFuel >= target) {
                    0
                } else {
                    -1
                }
            }

            if (stations[0][0] > startFuel) {
                return -1
            }

            //station index - fill fuel times
            val dp = Array(stations.size) {
                IntArray(stations.size + 1) { -1 }
            }

            for ((index, station) in stations.withIndex()) {
                if (startFuel >= station[0]) {
                    dp[index][0] = startFuel - station[0]
                } else {
                    break
                }
            }

            val lastDistance = target - stations[stations.lastIndex][0]

            if (dp[dp.lastIndex][0] >= lastDistance) {
                return 0
            }

            dp[0][1] = dp[0][0] + stations[0][1]

            if (dp[dp.lastIndex][1] >= lastDistance) {
                return 1
            }

            loop@ for (fillFuelCount in 1..stations.size) {
                var remaining: Int = -1
                for (stationIndex in (fillFuelCount - 1).coerceAtLeast(1) until stations.size) {
                    val currentDp = dp[stationIndex]

                    remaining = -1

                    val driveDistance = stations[stationIndex][0] - stations[stationIndex - 1][0]
                    if (dp[stationIndex - 1][fillFuelCount - 1] >= driveDistance) {
                        remaining = dp[stationIndex - 1][fillFuelCount - 1] - driveDistance + stations[stationIndex][1]
                    }

                    if (dp[stationIndex - 1][fillFuelCount] >= driveDistance) {
                        remaining = remaining.coerceAtLeast(dp[stationIndex - 1][fillFuelCount] - driveDistance)
                    }

                    if (remaining >= 0) {
                        currentDp[fillFuelCount] = remaining
                    } else {
                        continue@loop
                    }
                }

                if (lastDistance <= remaining) {
                    return fillFuelCount
                }
            }

            return -1
        }
    }

    expect {
        Solution().minRefuelStops(
            100, 50, arrayOf(
                intArrayOf(50, 50)
            )
        )

    }
}