package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minPartitions(n: String): Int {
            return n.max() - '0'
        }
    }

    measureTimeMillis {
        Solution().minPartitions(
            "32"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}

