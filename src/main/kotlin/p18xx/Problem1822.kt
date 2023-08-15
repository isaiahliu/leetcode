package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun arraySign(nums: IntArray): Int {
            var sign = 1
            nums.forEach {
                when {
                    it < 0 -> {
                        sign = -sign
                    }

                    it == 0 -> {
                        return 0
                    }
                }
            }

            return sign
        }
    }
    measureTimeMillis {
        Solution().arraySign(
            intArrayOf(1)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
