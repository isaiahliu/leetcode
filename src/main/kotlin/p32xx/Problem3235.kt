package p32xx

import util.expect
import kotlin.math.abs

fun main() {
    class Solution {
        fun canReachCorner(xCorner: Int, yCorner: Int, circles: Array<IntArray>): Boolean {
            val visited = BooleanArray(circles.size)
            for (i in circles.indices) {
                val (x, y, r) = circles[i]

                if (pointInCircle(0, 0, x.toLong(), y.toLong(), r.toLong()) || pointInCircle(xCorner.toLong(), yCorner.toLong(), x.toLong(), y.toLong(), r.toLong())) {
                    return false
                }
                if (!visited[i] && circleIntersectsTopLeftOfRectangle(x, y, r, xCorner, yCorner)
                    && dfs(i, circles, xCorner, yCorner, visited)
                ) {
                    return false
                }
            }
            return true
        }

        fun dfs(i: Int, circles: Array<IntArray>, xCorner: Int, yCorner: Int, visited: BooleanArray): Boolean {
            val x1 = circles[i][0]
            val y1 = circles[i][1]
            val r1 = circles[i][2]
            if (circleIntersectsBottomRightOfRectangle(x1, y1, r1, xCorner, yCorner)) {
                return true
            }
            visited[i] = true
            for (j in circles.indices) {
                if (!visited[j]) {
                    val (x2, y2, r2) = circles[j]
                    if (circlesIntersectInRectangle(x1.toLong(), y1.toLong(), r1.toLong(), x2.toLong(), y2.toLong(), r2.toLong(), xCorner.toLong(), yCorner.toLong())
                        && dfs(j, circles, xCorner, yCorner, visited)
                    ) {
                        return true
                    }
                }
            }
            return false
        }

        fun pointInCircle(px: Long, py: Long, x: Long, y: Long, r: Long): Boolean {
            return (x - px) * (x - px) + (y - py) * (y - py) <= r * r
        }

        fun circleIntersectsTopLeftOfRectangle(x: Int, y: Int, r: Int, xCorner: Int, yCorner: Int): Boolean {
            return (abs(x.toDouble()) <= r && 0 <= y && y <= yCorner) ||
                    (x in 0..xCorner && abs((y - yCorner).toDouble()) <= r) ||
                    pointInCircle(x.toLong(), y.toLong(), 0, yCorner.toLong(), r.toLong())
        }

        fun circleIntersectsBottomRightOfRectangle(x: Int, y: Int, r: Int, xCorner: Int, yCorner: Int): Boolean {
            return (abs(y.toDouble()) <= r && 0 <= x && x <= xCorner) ||
                    (y in 0..yCorner && abs((x - xCorner).toDouble()) <= r) ||
                    pointInCircle(x.toLong(), y.toLong(), xCorner.toLong(), 0, r.toLong())
        }

        fun circlesIntersectInRectangle(x1: Long, y1: Long, r1: Long, x2: Long, y2: Long, r2: Long, xCorner: Long, yCorner: Long): Boolean {
            return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= (r1 + r2) * (r1 + r2) && x1 * r2 + x2 * r1 < (r1 + r2) * xCorner && y1 * r2 + y2 * r1 < (r1 + r2) * yCorner
        }
    }

    expect {
        Solution().canReachCorner(
            5, 1, arrayOf()
        )
    }
}
