package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        val cache = hashMapOf<Pair<Int, Int>, Int>()

        fun tilingRectangle(n: Int, m: Int): Int {
            val width = m.coerceAtLeast(n)
            val height = m.coerceAtMost(n)

            val cacheKey = width to height

            return when {
                height == 0 -> {
                    0
                }

                width % height == 0 -> {
                    width / height
                }

                cacheKey in cache -> {
                    cache[cacheKey] ?: 0
                }

                else -> {
                    var result = 1 + tilingRectangle(width - height, height)

                    var left = height - 1

                    while (left >= width - left) {
                        val right = width - left

                        var t = tilingRectangle(left, height - left) + tilingRectangle(right, height - right)

                        var leftTopWidth = left + 1
                        while (leftTopWidth <= width) {
                            t = t.coerceAtMost(
                                tilingRectangle(leftTopWidth, height - left) + tilingRectangle(
                                    width - leftTopWidth, height - right
                                ) + tilingRectangle(leftTopWidth - left, left - right)
                            )

                            leftTopWidth++
                        }

                        result = result.coerceAtMost(2 + t)

                        cache[cacheKey] = result
                        left--
                    }

                    result
                }
            }
        }
    }

    measureTimeMillis {
        println(Solution().tilingRectangle(11, 13))
    }.also { println("Time cost: ${it}ms") }
}

