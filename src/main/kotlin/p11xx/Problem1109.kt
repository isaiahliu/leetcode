package p11xx

import util.expect

fun main() {
    class Solution {
        fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
            val result = IntArray(n)

            bookings.forEach { (from, to, seats) ->
                (from..to).forEach {
                    result[it - 1] += seats
                }
            }

            return result
        }
    }

    expect {
        Solution().corpFlightBookings(
            arrayOf(), 1
        )

    }
}