package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rectangleArea(rectangles: Array<IntArray>): Int {
            val m = 1000000007

            class Grid(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
                val square: Long get() = 1L * (x2 - x1) * (y2 - y1) % m

                fun overlap(target: Grid): Boolean {
                    val xMatch =
                        x1 in target.x1 until target.x2 || x2 in target.x1 + 1..target.x2 || target.x1 in x1 until x2 || target.x2 in x1 + 1..x2

                    val yMatch =
                        y1 in target.y1 until target.y2 || y2 in target.y1 + 1..target.y2 || target.y1 in y1 until y2 || target.y2 in y1 + 1..y2

                    return xMatch && yMatch
                }

                fun contains(target: Grid): Boolean {
                    return xContains(target) && yContains(target)
                }

                fun xContains(target: Grid): Boolean {
                    return target.x1 >= x1 && target.x2 <= x2
                }

                fun yContains(target: Grid): Boolean {
                    return target.y1 >= y1 && target.y2 <= y2
                }

                fun breakIntoPieces(target: Grid): Set<Grid> {
                    val result = hashSetOf(this)
                    when {
                        contains(target) -> {}
                        xContains(target) -> {
                            if (target.y1 < y1) {
                                result.add(Grid(target.x1, target.y1, target.x2, y1))
                            }

                            if (target.y2 > y2) {
                                result.add(Grid(target.x1, y2, target.x2, target.y2))
                            }
                        }

                        yContains(target) -> {
                            if (target.x1 < x1) {
                                result.add(Grid(target.x1, target.y1, x1, target.y2))
                            }

                            if (target.x2 > x2) {
                                result.add(Grid(x2, target.y1, target.x2, target.y2))
                            }
                        }

                        target.xContains(this) || target.yContains(this) -> return target.breakIntoPieces(this)
                        target.x1 < x1 && target.y1 < y1 -> {
                            result.add(Grid(target.x1, y1, x1, target.y2))
                            result.add(Grid(target.x1, target.y1, target.x2, y1))
                        }

                        target.x1 < x1 && target.y2 > y2 -> {
                            result.add(Grid(target.x1, target.y1, x1, y2))
                            result.add(Grid(target.x1, y2, target.x2, target.y2))
                        }

                        target.x1 > x1 && target.y1 < y1 -> {
                            result.add(Grid(target.x1, target.y1, x2, y1))
                            result.add(Grid(x2, target.y1, target.x2, target.y2))
                        }

                        target.x1 > x1 && target.y2 > y2 -> {
                            result.add(Grid(target.x1, y2, x2, target.y2))
                            result.add(Grid(x2, target.y1, target.x2, target.y2))
                        }
                    }

                    return result
                }

                override fun toString(): String {
                    return "${x1},${y1}-${x2},${y2}"
                }
            }

            val queue = LinkedList(rectangles.map { Grid(it[0], it[1], it[2], it[3]) })
            val grids = hashSetOf<Grid>()

            while (true) {
                val next = queue.poll() ?: break

                val overlap = grids.firstOrNull { it.overlap(next) }

                if (overlap == null) {
                    grids.add(next)
                } else {
                    grids.remove(overlap)
                    queue.addAll(overlap.breakIntoPieces(next))
                }
            }

            return (grids.map { it.square }.sum() % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().rectangleArea(
            arrayOf(
                intArrayOf(2, 58, 59, 89),
                intArrayOf(75, 35, 94, 43),
                intArrayOf(21, 3, 92, 62),
                intArrayOf(51, 75, 72, 91)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}