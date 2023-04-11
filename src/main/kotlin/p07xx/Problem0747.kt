package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun dominantIndex(nums: IntArray): Int {
            var maxIndex = 0
            var max = Int.MIN_VALUE
            var second = Int.MIN_VALUE

            nums.forEachIndexed { index, num ->
                if (num > max) {
                    second = max
                    max = num
                    maxIndex = index
                } else if (num > second) {
                    second = num
                }
            }

            return maxIndex.takeIf { max >= second * 2 } ?: -1
        }
    }

    measureTimeMillis {
        Solution().dominantIndex(
            intArrayOf(1, 2, 3, 4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}