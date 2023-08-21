package p04xx

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random
import util.expect

fun main() {
    class Solution(val radius: Double, val x_center: Double, val y_center: Double) {
        fun randPoint(): DoubleArray {
            val angle = Random.nextDouble(PI * 2)
            val length = sqrt(Random.nextDouble(radius * radius))

            val deltaX = cos(angle) * length
            val deltaY = sin(angle) * length

            return doubleArrayOf(x_center + deltaX, y_center + deltaY)
        }
    }
    expect {
        val sol = Solution(1.0, 0.0, 0.0)
        repeat(1000) {
            sol.randPoint().toList().also { (x, y) ->
                "${x},${y} -- ${sqrt(x * x + y * y)}"
            }
        }
    }
}