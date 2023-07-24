package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numJewelsInStones(jewels: String, stones: String): Int {
            return jewels.toSet().let { stones.count { stone -> stone in it } }
        }
    }

    measureTimeMillis {
        Solution().numJewelsInStones(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}