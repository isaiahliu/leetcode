package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kLengthApart(nums: IntArray, k: Int): Boolean {
            var last = -1
            nums.forEachIndexed { index, i ->
                if (i == 1) {
                    if (last > -1 && index - last <= k) {
                        return false
                    }
                    last = index
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().kLengthApart(
            intArrayOf(), 5
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

