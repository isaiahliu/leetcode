package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
            val distanceMap = hashMapOf<Int, Int>()

            val arr = arrayOf(p1, p2, p3, p4)

            for (i in arr.indices) {
                for (j in i + 1 until arr.size) {
                    val (x1, y1) = arr[i]
                    val (x2, y2) = arr[j]

                    val d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)
                    distanceMap[d] = (distanceMap[d] ?: 0) + 1
                }
            }

            val side = distanceMap.entries.firstOrNull { it.value == 4 }?.key ?: return false
            val diagonal = distanceMap.entries.firstOrNull { it.value == 2 }?.key ?: return false

            return side * 2 == diagonal
        }
    }

    measureTimeMillis {
        Solution().validSquare(intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(1, 0), intArrayOf(0, 1))
            .also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}