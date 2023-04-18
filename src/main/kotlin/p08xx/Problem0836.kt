package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
            val (x11, y11, x12, y12) = rec1
            val (x21, y21, x22, y22) = rec2

            val xMatch = x11 in x21 until x22 || x12 in x21 + 1..x22 || x21 in x11 until x12 || x22 in x11 + 1..x12
            val yMatch = y11 in y21 until y22 || y12 in y21 + 1..y22 || y21 in y11 until y12 || y22 in y11 + 1..y12

            return xMatch && yMatch
        }
    }

    measureTimeMillis {
        Solution().isRectangleOverlap(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}