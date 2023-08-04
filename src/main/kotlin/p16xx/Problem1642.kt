package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun furthestBuilding(heights: IntArray, bricks: Int, ladders: Int): Int {
            val diff = IntArray(heights.size) {
                if (it > 0 && heights[it] > heights[it - 1]) {
                    heights[it] - heights[it - 1]
                } else {
                    0
                }
            }

            fun binarySearch(start: Int, end: Int): Int {
                if (start > end) {
                    return Int.MIN_VALUE
                }

                val mid = (start + end) / 2

                return if (bricks >= diff.take(mid + 1).sortedDescending().drop(ladders).sum()) {
                    mid.coerceAtLeast(binarySearch(mid + 1, end))
                } else {
                    binarySearch(start, mid - 1)
                }
            }

            return binarySearch(0, heights.lastIndex)
        }
    }

    measureTimeMillis {
        Solution().furthestBuilding(
            intArrayOf(15, 88), 5, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}