package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isOneBitCharacter(bits: IntArray): Boolean {
            var followCount = 0
            bits.dropLast(1).forEach {
                if (followCount-- == 0) {
                    followCount = it
                }
            }

            return followCount == 0
        }
    }

    measureTimeMillis {
        Solution().isOneBitCharacter(
            intArrayOf(1, 0, 0)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}