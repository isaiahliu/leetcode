package p18xx

import java.util.*
import util.expect

fun main() {
    class SeatManager(n: Int) {
        val seats = TreeSet<Int>()

        init {
            repeat(n) {
                seats.add(it + 1)
            }
        }

        fun reserve(): Int {
            return seats.pollFirst() ?: -1
        }

        fun unreserve(seatNumber: Int) {
            seats.add(seatNumber)
        }
    }

    expect {
        SeatManager(1).reserve()

    }
}
