package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun decode(encoded: IntArray, first: Int): IntArray {
            var t = first

            return IntArray(encoded.size + 1) {
                if (it > 0) {
                    t = t xor encoded[it - 1]
                }

                t
            }
        }
    }

    measureTimeMillis {
        Solution().decode(
            intArrayOf(), 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
