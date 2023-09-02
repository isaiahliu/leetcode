package p22xx

import util.expect

fun main() {
    class Solution {
        fun countLatticePoints(circles: Array<IntArray>): Int {
            val points = hashSetOf<Pair<Int, Int>>()

            circles.forEach { (x, y, r) ->
                (x - r..x + r).forEach { px ->
                    (y - r..y + r).forEach { py ->
                        if ((px - x) * (px - x) + (py - y) * (py - y) <= r * r) {
                            points.add(px to py)
                        }
                    }
                }
            }

            return points.size
        }
    }

    expect {
        Solution().countLatticePoints(
            arrayOf()
        )
    }
}