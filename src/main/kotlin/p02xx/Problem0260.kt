package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun singleNumber(nums: IntArray): IntArray {
            val r = Integer.highestOneBit(nums.reduce { a, b -> a xor b })

            var r1 = 0
            var r2 = 0

            nums.forEach {
                if (it and r == 0) {
                    r1 = r1 xor it
                } else {
                    r2 = r2 xor it
                }
            }

            return intArrayOf(r1, r2)
        }
    }

    measureTimeMillis {
        Solution().singleNumber(
            intArrayOf()
        ).also { println(it) }
    }
}

