package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxPoints(points: Array<IntArray>): Int {
            if (points.size <= 2) {
                return points.size
            }
            val map = hashMapOf<Pair<Pair<Int, Int>, Int>, MutableSet<Pair<Int, Int>>>()
            for (i in points.indices) {
                for (j in i + 1 until points.size) {
                    val (x1, y1) = points[i]
                    val (x2, y2) = points[j]

                    val deltaY = y2 - y1
                    val deltaX = x2 - x1

                    val line = when {
                        deltaY == 0 -> {
                            Int.MAX_VALUE to y1 to 0
                        }

                        deltaX == 0 -> {
                            x1 to Int.MAX_VALUE to 0
                        }

                        else -> {
                            //y = ax + b
                            //a = deltaY / deltaX
                            //deltaGcd = gcd(deltaX, deltaY)
                            //dy = deltaY / deltaGcd
                            //dx = deltaX / deltaGcd
                            //dx * b = y * dx - x * dy
                            //key = dx to dy to dx * b

                            val gcd = deltaY.toBigInteger().gcd(deltaX.toBigInteger()).toInt()

                            val dy = deltaY / gcd
                            val dx = deltaX / gcd

                            val dxb = y1 * dx - x1 * dy

                            dx to dy to dxb
                        }
                    }

                    map.computeIfAbsent(line) { hashSetOf() }.also {
                        it.add(x1 to y1)
                        it.add(x2 to y2)
                    }
                }
            }

            return map.values.map { it.size }.max()
        }
    }

    measureTimeMillis {
        Solution().maxPoints(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(2, 2),
                intArrayOf(1, 0),
                intArrayOf(2, 0)
            )
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

