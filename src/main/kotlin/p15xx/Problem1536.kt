package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSwaps(grid: Array<IntArray>): Int {
            val sizes = IntArray(grid.size) { 0 }

            grid.forEachIndexed { row, arr ->
                for (i in arr.lastIndex downTo 1) {
                    if (arr[i] == 0) {
                        sizes[row]++
                    } else {
                        break
                    }
                }
            }

            var result = 0
            loop@ for (size in grid.size - 1 downTo 1) {
                for ((index, s) in sizes.withIndex()) {
                    if (s >= size) {
                        sizes[index] = -1
                        continue@loop
                    } else if (s >= 0) {
                        result++
                    }
                }
                return -1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minSwaps(
            arrayOf()
        ).also { println(it) }
    }
}

