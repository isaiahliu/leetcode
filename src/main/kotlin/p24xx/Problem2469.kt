package p24xx

import util.expect

fun main() {
    class Solution {
        fun convertTemperature(celsius: Double): DoubleArray {
            return doubleArrayOf(celsius + 273.15, celsius * 1.8 + 32.0)
        }
    }

    expect {
        Solution().convertTemperature(
            15.0
        )
    }
}