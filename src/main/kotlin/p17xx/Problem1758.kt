package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperations(s: String): Int {
            val cost = intArrayOf(0, 0)
            s.forEachIndexed { index, c ->
                cost.indices.forEach {
                    cost[it] += (index xor (c - '0') xor it) and 1
                }
            }

            return cost.min()
        }
    }

    measureTimeMillis {
        Solution().minOperations(
            "0100"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
