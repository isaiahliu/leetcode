package p09xx

import util.expect

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

    expect {
        val rle = RLEIterator(intArrayOf(3, 8, 0, 9, 2, 5))
        rle.next(2)
        rle.next(1)
        rle.next(1)
        rle.next(2)
    }
}