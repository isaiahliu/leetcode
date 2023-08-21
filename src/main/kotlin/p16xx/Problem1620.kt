package p16xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun bestCoordinate(towers: Array<IntArray>, radius: Int): IntArray {
            val result = intArrayOf(0, 0)

            if (towers.all { it[2] == 0 }) {
                return result
            }

            var max = -1

            val xs = towers.map { it[0] }
            val ys = towers.map { it[1] }

            for (x in xs.min()..xs.max()) {
                for (y in ys.min()..ys.max()) {
                    var strength = 0
                    towers.forEach { (tx, ty, str) ->
                        val d2 = (tx - x) * (tx - x) + (ty - y) * (ty - y)

                        if (d2 <= radius * radius) {
                            strength += (str / (1 + sqrt(d2.toDouble()))).toInt()
                        }
                    }

                    if (strength > max) {
                        result[0] = x
                        result[1] = y
                        max = strength
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().bestCoordinate(
            arrayOf(
                intArrayOf(1, 2, 5),
                intArrayOf(2, 1, 7),
                intArrayOf(3, 1, 9),
            ), 2
        ).toList()
    }
}