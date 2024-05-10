package p29xx

import util.expect

fun main() {
    class Solution {
        fun countTestedDevices(batteryPercentages: IntArray): Int {
            return batteryPercentages.fold(0) { count, p ->
                count + (p > count).compareTo(false)
            }
        }
    }

    expect {
        Solution().countTestedDevices(
            intArrayOf(7, 12, 9, 8, 9, 15)
        )
    }
}
