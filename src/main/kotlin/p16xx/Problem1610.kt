package p16xx

import kotlin.math.PI
import kotlin.math.atan
import util.expect

fun main() {
    class Solution {
        fun visiblePoints(points: List<List<Int>>, angle: Int, location: List<Int>): Int {
            if (angle == 360) {
                return points.size
            }

            fun calculateAngle(deltaX: Int, deltaY: Int): Double? {
                if (deltaX == 0) {
                    return if (deltaY > 0) {
                        90.0
                    } else if (deltaY < 0) {
                        -90.0
                    } else {
                        null
                    }
                }

                var result = atan(deltaY.toDouble() / deltaX) / PI * 180

                if (deltaX < 0) {
                    if (deltaY < 0) {
                        result -= 180.0
                    } else {
                        result += 180.0
                    }
                }

                return result
            }

            val (baseX, baseY) = location
            val angles = points.mapNotNull { (x, y) ->
                calculateAngle(x - baseX, y - baseY)
            }.sortedBy { it }

            var result = points.size - angles.size
            var max = 0

            if (angles.size > 1) {
                max++
                var left = 0
                var right = 1

                while (left < angles.size) {
                    val leftAngle = angles[left]
                    while (right != left) {
                        if ((angles[right] - leftAngle + 360) % 360 <= angle) {
                            right++
                            right %= angles.size
                        } else {
                            break
                        }
                    }

                    if (right == left) {
                        max = angles.size
                        break
                    }

                    max = max.coerceAtLeast((right - left + angles.size) % angles.size)

                    left++
                    if (right == left) {
                        right++
                        right %= angles.size
                    }
                }
            } else {
                max += angles.size
            }

            return result + max
        }
    }

    expect {
        Solution().visiblePoints(
            listOf(listOf(1, 0), listOf(2, 1)), 13, listOf(1, 1)
        )
    }
}

