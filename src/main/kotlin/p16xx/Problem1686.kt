package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun stoneGameVI(aliceValues: IntArray, bobValues: IntArray): Int {
            var alice = 0
            var bob = 0
            aliceValues.indices.sortedByDescending { aliceValues[it] + bobValues[it] }.forEachIndexed { index, i ->
                if (index % 2 == 0) {
                    alice += aliceValues[i]
                } else {
                    bob += bobValues[i]
                }
            }

            return alice.compareTo(bob)
        }
    }

    measureTimeMillis {
        Solution().stoneGameVI(
            intArrayOf(), intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

