package p18xx

import java.util.*
import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        SeatManager(1).reserve().also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
