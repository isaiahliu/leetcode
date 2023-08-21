package p17xx

import util.expect

fun main() {
    class Solution {
        fun averageWaitingTime(customers: Array<IntArray>): Double {
            var waitingTime = 0L

            var finishTime = 0L

            customers.forEach { (arrive, cookTime) ->
                finishTime = finishTime.coerceAtLeast(arrive.toLong()) + cookTime

                waitingTime += finishTime - arrive
            }

            return waitingTime.toDouble() / customers.size
        }
    }

    expect {
        Solution().averageWaitingTime(
            arrayOf()
        )
    }
}
