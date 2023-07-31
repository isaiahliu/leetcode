package p15xx

import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMinDistSum(positions: Array<IntArray>): Double {
            val eps = 1e-7
            var alpha = 1.0
            val decay = 1e-3
            val n = positions.size
            var x = 0.0
            var y = 0.0
            for (pos in positions) {
                x += pos[0].toDouble()
                y += pos[1].toDouble()
            }
            x /= n.toDouble()
            y /= n.toDouble()

            while (true) {
                for (i in 0 until n) {
                    val randIndex: Int = Random.nextInt(n)
                    val rx = positions[i][0]
                    val ry = positions[i][1]
                    positions[i][0] = positions[randIndex][0]
                    positions[i][1] = positions[randIndex][1]
                    positions[randIndex][0] = rx
                    positions[randIndex][1] = ry
                }

                val xPrev = x
                val yPrev = y
                var i = 0
                while (i < n) {
                    val j = (i + n).toDouble().coerceAtMost(n.toDouble()).toInt()
                    var dx = 0.0
                    var dy = 0.0
                    for (k in i until j) {
                        val pos = positions[k]
                        dx += (x - pos[0]) / (sqrt((x - pos[0]) * (x - pos[0]) + (y - pos[1]) * (y - pos[1])) + eps)
                        dy += (y - pos[1]) / (sqrt((x - pos[0]) * (x - pos[0]) + (y - pos[1]) * (y - pos[1])) + eps)
                    }
                    x -= alpha * dx
                    y -= alpha * dy

                    alpha *= 1.0 - decay
                    i += n
                }

                if (sqrt((x - xPrev) * (x - xPrev) + (y - yPrev) * (y - yPrev)) < eps) {
                    break
                }
            }

            var result = 0.0
            for (pos in positions) {
                result += sqrt((pos[0] - x) * (pos[0] - x) + (pos[1] - y) * (pos[1] - y))
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().getMinDistSum(
            arrayOf()
        ).also { println(it) }
    }
}

