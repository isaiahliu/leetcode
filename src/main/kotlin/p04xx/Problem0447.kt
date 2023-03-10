package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numberOfBoomerangs(points: Array<IntArray>): Int {
            var result = 0

            for (i in points.indices) {
                val counts = hashMapOf<Int, Int>()
                val (x1, y1) = points[i]
                for (j in points.indices) {
                    val (x2, y2) = points[j]

                    val distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)

                    counts[distance] = (counts[distance] ?: 0) + 1
                }

                result += counts.values.filter { it > 1 }.map { it * (it - 1) }.sum()
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numberOfBoomerangs(
            arrayOf(
                intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1)
//                intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(2, 0)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}