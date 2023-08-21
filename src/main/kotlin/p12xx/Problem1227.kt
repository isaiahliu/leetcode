package p12xx

import util.expect

fun main() {
    class Solution {
        fun nthPersonGetsNthSeat(n: Int): Double {
            if (n == 1) {
                return 1.0
            }

            var next = 1.0 / n
            var remainingSeat = n - 1

            repeat(n - 2) {
                next += next / remainingSeat
                remainingSeat--
            }

            return 1 - next
        }
    }

    expect {
        Solution().nthPersonGetsNthSeat(
            2
        )
    }
}

