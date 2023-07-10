package p11xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().corpFlightBookings(
            arrayOf(), 1
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}