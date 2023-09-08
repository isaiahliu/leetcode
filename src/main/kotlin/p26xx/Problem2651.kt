package p26xx

import util.expect

fun main() {
    class Solution {
        fun findDelayedArrivalTime(arrivalTime: Int, delayedTime: Int): Int {
            return (arrivalTime + delayedTime) % 24
        }
    }

    expect {
        Solution().findDelayedArrivalTime(
            1, 2
        )
    }
}