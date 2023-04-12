package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reachingPoints(sx: Int, sy: Int, tx: Int, ty: Int): Boolean {
            return when {
                sx == tx && sy == ty -> {
                    true
                }

                sx == 0 -> {
                    ty == sy && tx == sy
                }

                sy == 0 -> {
                    tx == sx && ty == sx
                }

                sx > tx || sy > ty -> {
                    false
                }

                tx == ty -> {
                    false
                }

                else -> {
                    var x = tx
                    var y = ty

                    while (x > sx && y > sy) {
                        if (x > y) {
                            x %= y
                        } else {
                            y %= x
                        }
                    }

                    when {
                        x == sx -> {
                            (y - sy) % x == 0
                        }

                        y == sy -> {
                            (x - sx) % y == 0
                        }

                        else -> {
                            false
                        }
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().reachingPoints(
            1, 8, 4, 15
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}