package p20xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minMovesToSeat(seats: IntArray, students: IntArray): Int {
            seats.sort()
            students.sort()

            return seats.indices.map {
                (seats[it] - students[it]).absoluteValue
            }.sum()
        }
    }

    expect {
        Solution().minMovesToSeat(
            intArrayOf(3, 1, 5), intArrayOf(2, 7, 4)
        )
    }
}