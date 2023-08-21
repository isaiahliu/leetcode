package p03xx

import kotlin.math.abs
import util.expect

fun main() {
    class Solution {
        fun isPerfectSquare(num: Int): Boolean {
            val e = 1e-10
            var t = num.toDouble()

            while (abs(t - num / t) > e * t) {
                t = (num / t + t) / 2.0
            }

            val result = t.toInt()

            return result * result == num
        }
    }

    expect {
        Solution().isPerfectSquare(
            16
        )
    }
}

