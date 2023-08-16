package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun memLeak(memory1: Int, memory2: Int): IntArray {
            val result = intArrayOf(0, memory1, memory2)

            while (true) {
                result[0]++

                val useIndex = if (result[2] > result[1]) 2 else 1

                if (result[useIndex] >= result[0]) {
                    result[useIndex] -= result[0]
                } else {
                    break
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().memLeak(
            2, 2
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
