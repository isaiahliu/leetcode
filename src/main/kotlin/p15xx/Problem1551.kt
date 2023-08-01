package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperations(n: Int): Int {
            return n * n / 4
        }
    }

    measureTimeMillis {
        Solution().minOperations(
            5
        ).also { println(it) }
    }
}

