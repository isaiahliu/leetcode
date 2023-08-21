package p12xx

import util.expect

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

                        var topLeftWidth = left + 1
                        while (topLeftWidth <= width) {
                            t = t.coerceAtMost(
                                tilingRectangle(topLeftWidth, height - left) + tilingRectangle(
                                    width - topLeftWidth, height - right
                                ) + tilingRectangle(topLeftWidth - left, left - right)
                            )

                            topLeftWidth++
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

    expect {
        Solution().tilingRectangle(11, 13)
    }
}

