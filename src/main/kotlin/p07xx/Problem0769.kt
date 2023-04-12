package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxChunksToSorted(arr: IntArray): Int {
            var result = 0

            var targetIndex = -1
            arr.forEachIndexed { index, num ->
                targetIndex = targetIndex.coerceAtLeast(num)

                if (index == targetIndex) {
                    result++
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxChunksToSorted(
            intArrayOf(1, 0, 2, 3, 4)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}