package p10xx

import util.expect

fun main() {
    class Solution {
        fun isBoomerang(points: Array<IntArray>): Boolean {
            val (p1, p2, p3) = points

            val (x1, y1) = p1
            val (x2, y2) = p2
            val (x3, y3) = p3

            return (y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1)
        }
    }

    expect {
        Solution().isBoomerang(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
            )
        )
    }
}
