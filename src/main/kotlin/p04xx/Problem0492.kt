package p04xx

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun constructRectangle(area: Int): IntArray {
            var w = sqrt(area.toDouble()).toInt()

            while (area % w != 0) {
                w--
            }

            return intArrayOf(area / w, w)
        }
    }

    measureTimeMillis {
        Solution().constructRectangle(
            122122
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}