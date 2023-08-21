package p13xx

import util.expect

fun main() {
    class Solution {
        fun angleClock(hour: Int, minutes: Int): Double {
            val m = minutes * 6.0
            val h = (hour + minutes / 60.0) * 30.0

            val a = (360 + h - m) % 360

            return a.coerceAtMost(360 - a)
        }
    }

    expect {
        Solution().angleClock(
            12, 30
        )
    }
}

