package p02xx

import util.expect

fun main() {
    class Solution {
        fun computeArea(ax1: Int, ay1: Int, ax2: Int, ay2: Int, bx1: Int, by1: Int, bx2: Int, by2: Int): Int {
            val sum = (ay2 - ay1) * (ax2 - ax1) + (by2 - by1) * (bx2 - bx1)

            val coveredX = hashSetOf<Int>()
            coveredX.addAll(arrayOf(ax1, ax2).filter { it in bx1..bx2 })
            coveredX.addAll(arrayOf(bx1, bx2).filter { it in ax1..ax2 })

            val coveredY = hashSetOf<Int>()
            coveredY.addAll(arrayOf(ay1, ay2).filter { it in by1..by2 })
            coveredY.addAll(arrayOf(by1, by2).filter { it in ay1..ay2 })

            return if (coveredX.isEmpty() || coveredY.isEmpty()) {
                sum
            } else {
                sum - (coveredX.max() - coveredX.min()) * (coveredY.max() - coveredY.min())
            }
        }
    }

    expect {
        Solution().computeArea(
            -2, -2, 2, 2, -1, 4, 1, 6
        )
    }
}

