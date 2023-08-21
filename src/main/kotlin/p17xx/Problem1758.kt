package p17xx

import util.expect

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

    expect {
        Solution().minOperations(
            "0100"
        )
    }
}
