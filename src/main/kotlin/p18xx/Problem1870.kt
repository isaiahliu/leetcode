package p18xx

import kotlin.math.ceil
import util.expect

fun main() {
    class Solution {
        fun minSpeedOnTime(dist: IntArray, hour: Double): Int {
            fun binarySearch(min: Int, max: Int): Int {
                if (min > max) {
                    return Int.MAX_VALUE
                }

                val mid = min + (max - min) / 2

                val cost = dist.fold(0.0) { used, distance ->
                    ceil(used) + distance.toDouble() / mid
                }

                return if (cost <= hour) {
                    mid.coerceAtMost(binarySearch(min, mid - 1))
                } else {
                    binarySearch(mid + 1, max)
                }
            }

            return binarySearch(1, 10000000).takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    expect {
        Solution().minSpeedOnTime(
            intArrayOf(1, 3, 2), 6.0
        )
    }
}
