package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minFallingPathSum(grid: Array<IntArray>): Int {
            if (grid[0].size == 1) {
                return grid.map { it[0] }.sum()
            }

            //minIndex - min - secondMin
            val mins = intArrayOf(0, 0, 0)

            grid.forEach {
                val (minIndex, min, secondMin) = mins
                mins[1] = Int.MAX_VALUE
                mins[2] = Int.MAX_VALUE
                it.forEachIndexed { index, num ->
                    val sum = num + if (minIndex == index) secondMin else min

                    if (sum < mins[1]) {
                        mins[0] = index
                        mins[2] = mins[1]
                        mins[1] = sum
                    } else if (sum < mins[2]) {
                        mins[2] = sum
                    }
                }
            }

            return mins[1]
        }
    }

    measureTimeMillis {
        Solution().minFallingPathSum(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
