package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class RLEIterator(val encoding: IntArray) {
        var index = 0
        fun next(n: Int): Int {
            var t = n
            while ((encoding.getOrNull(index) ?: return -1) < t) {
                t -= encoding[index]
                index += 2
            }

            encoding[index] -= t

            return encoding[index + 1]
        }
    }

    measureTimeMillis {
        val rle = RLEIterator(intArrayOf(3, 8, 0, 9, 2, 5))
        rle.next(2).also { println(it) }
        rle.next(1).also { println(it) }
        rle.next(1).also { println(it) }
        rle.next(2).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}