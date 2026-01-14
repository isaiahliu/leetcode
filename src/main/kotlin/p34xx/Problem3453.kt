package p34xx

import util.expect
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    class Solution {
        fun separateSquares(squares: Array<IntArray>): Double {
            var maxY = 0.0
            var totalA = 0.0
            for (sq in squares) {
                val y = sq[1]
                val l = sq[2]
                totalA += l.toDouble() * l
                maxY = max(maxY, (y + l).toDouble())
            }

            var lo = 0.0
            var hi = maxY
            val eps = 1e-5
            while (abs(hi - lo) > eps) {
                val mid = (hi + lo) / 2
                if (check(mid, squares, totalA)) {
                    hi = mid
                } else {
                    lo = mid
                }
            }

            return hi
        }

        private fun check(limitY: Double, squares: Array<IntArray>, totalA: Double): Boolean {
            var area = 0.0
            for (sq in squares) {
                val y = sq[1]
                val l = sq[2]
                if (y < limitY) {
                    area += l.toDouble() * min(limitY - y, l.toDouble())
                }
            }
            return area >= totalA / 2
        }
    }

    expect {
        Solution().separateSquares(
            arrayOf()
        )
    }
}
