package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumTime(time: IntArray, totalTrips: Int): Long {
            var left = 1L
            var right = time.max().toLong() * totalTrips

            var result = 0L

            while (left <= right) {
                val mid = (left + right) / 2

                val trips = time.sumOf { mid / it }

                if (trips >= totalTrips) {
                    result = mid
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumTime(
            intArrayOf(1, 2, 3), 5
        )
    }
}