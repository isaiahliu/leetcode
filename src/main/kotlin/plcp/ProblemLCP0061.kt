package plcp

import util.expect

fun main() {
    class Solution {
        fun temperatureTrend(temperatureA: IntArray, temperatureB: IntArray): Int {
            var result = 0

            var day = 0
            (0 until temperatureA.lastIndex).forEach {
                val diffA = temperatureA[it + 1].compareTo(temperatureA[it])
                val diffB = temperatureB[it + 1].compareTo(temperatureB[it])

                if (diffA == diffB) {
                    day++
                    result = maxOf(result, day)
                } else {
                    day = 0
                }
            }

            return result
        }
    }

    expect {
        Solution().temperatureTrend(
            intArrayOf(3, 4, 5, 1, 6, 7), intArrayOf()
        )
    }
}
