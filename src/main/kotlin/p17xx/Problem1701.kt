package p17xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().averageWaitingTime(
            arrayOf()
        ).also { println("${it} should be ${it}") }
    }.also { println("Time cost: ${it}ms") }
}
