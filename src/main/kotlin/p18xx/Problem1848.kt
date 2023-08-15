package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMinDistance(nums: IntArray, target: Int, start: Int): Int {
            var offset = 0
            while (true) {
                arrayOf(start - offset, start + offset).forEach {
                    if (nums.getOrNull(it) == target) {
                        return offset
                    }
                }
                offset++
            }
        }
    }

    measureTimeMillis {
        Solution().getMinDistance(
            intArrayOf(2, 2), 1, 1
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
