package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun hammingDistance(x: Int, y: Int): Int {
            return Integer.bitCount(x xor y)
        }
    }

    measureTimeMillis {
        Solution().hammingDistance(1, 3).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}