package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun decode(encoded: IntArray): IntArray {
            var num = 1

            for (i in 1 until encoded.size step 2) {
                num = num xor i * 2
                num = num xor (i * 2 + 1)
                num = num xor encoded[i]
            }

            val result = IntArray(encoded.size + 1) {
                if (it > 0) {
                    num = num xor encoded[it - 1]
                }
                num
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().decode(
            intArrayOf(6, 5, 4, 6)
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
