package p09xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minAreaRect(points: Array<IntArray>): Int {
            val pointSet = points.map { it[0] to it[1] }.toSet()

            var result = Int.MAX_VALUE
            for (i in points.indices) {
                for (j in i + 1 until points.size) {
                    val (lr, lc) = points[i]
                    val (rr, rc) = points[j]

                    if (lr != rr && lc != rc) {
                        if (lr to rc in pointSet && rr to lc in pointSet) {
                            result = result.coerceAtMost((lr - rr).absoluteValue * (lc - rc).absoluteValue)
                        }
                    }
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: 0
        }
    }

    measureTimeMillis {
        Solution().minAreaRect(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}