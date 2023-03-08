package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun trapRainWater(heightMap: Array<IntArray>): Int {
            val h = heightMap.size
            val w = heightMap[0].size

            var top = 0

            val maxHeights = Array(heightMap.size) { r ->
                IntArray(heightMap[r].size) { c ->
                    top = top.coerceAtLeast(heightMap[r][c])

                    Int.MAX_VALUE
                }
            }

            val tasks = hashMapOf<Pair<Int, Int>, Int>()

            (1 until h).forEach { r ->
                tasks[r to 0] = heightMap[r][0]
                tasks[r to w - 1] = heightMap[r][w - 1]
            }

            (1 until w).forEach { c ->
                tasks[0 to c] = heightMap[0][c]
                tasks[h - 1 to c] = heightMap[h - 1][c]
            }

            while (tasks.isNotEmpty()) {
                tasks.entries.toList().also { tasks.clear() }.forEach { (pos, height) ->
                    val (r, c) = pos
                    maxHeights.getOrNull(r)?.getOrNull(c)?.also {
                        if (it > heightMap[r][c] && it > height) {
                            maxHeights[r][c] = heightMap[r][c].coerceAtLeast(height)

                            arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).forEach {
                                if ((tasks[it] ?: Int.MAX_VALUE) > maxHeights[r][c]) {
                                    tasks[it] = maxHeights[r][c]
                                }
                            }
                        }
                    }
                }
            }

            var result = 0
            (1 until h).forEach { r ->
                (1 until w).forEach { c ->
                    result += maxHeights[r][c].coerceAtMost(top) - heightMap[r][c]
                }
            }

            return result
        }
    }

    measureTimeMillis {
//        Solution().trapRainWater(
//            input.map {
//                it.split(",").map { it.toInt() }.toIntArray()
//            }.toTypedArray()
//        ).also { println(it) }
        Solution().trapRainWater(
            arrayOf(
                intArrayOf(1, 4, 3, 1, 3, 2),
                intArrayOf(3, 2, 1, 3, 2, 4),
                intArrayOf(2, 3, 3, 2, 3, 1),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


