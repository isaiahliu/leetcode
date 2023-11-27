package p25xx

import util.expect
import kotlin.math.max

fun main() {
    class Solution {
        fun minimizeSet(divisor1: Int, divisor2: Int, uniqueCnt1: Int, uniqueCnt2: Int): Int {
            val gcd = divisor1.toBigInteger().gcd(divisor2.toBigInteger()).toInt()
            val lcm = 1L * divisor1 * divisor2 / gcd

            var left = 1L
            var right = Int.MAX_VALUE.toLong()

            var result = Int.MAX_VALUE

            while (left <= right) {
                val mid = (0L + left + right) / 2

                val bad = mid / lcm
                val useless1 = mid / divisor1 - bad
                val useless2 = mid / divisor2 - bad
                val free = mid - useless1 - useless2 - bad

                if (max(0L, uniqueCnt1 - useless2) + max(0L, uniqueCnt2 - useless1) <= free) {
                    result = mid.toInt()
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minimizeSet(
            5, 5, 9, 3
        )
    }
}