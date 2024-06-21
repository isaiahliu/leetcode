package plcp

import util.expect

fun main() {
    class Solution {
        fun temperatureTrend(temperatureA: IntArray, temperatureB: IntArray): Int {
            var result = 0

            var day = 0
            (0 until temperatureA.lastIndex).forEach {
                if (temperatureA[it + 1].compareTo(temperatureA[it]) == temperatureB[it + 1].compareTo(temperatureB[it])) {
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
