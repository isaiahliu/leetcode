package p22xx

import util.expect

fun main() {
    class Solution {
        fun maximumWhiteTiles(tiles: Array<IntArray>, carpetLen: Int): Int {
            class SegNode(val start: Int, val end: Int) {
                var size = 0

                val children by lazy {
                    val mid = (start + end) / 2
                    arrayOf(
                        SegNode(start, mid),
                        SegNode(mid + 1, end),
                    )
                }

                fun cover(from: Int, to: Int) {
                    when {
                        from <= start && to >= end -> {
                            size = end - start + 1
                        }

                        from > end || to < start -> {
                            return
                        }

                        else -> {
                            size += to.coerceAtMost(end) - from.coerceAtLeast(start) + 1
                            children.forEach {
                                it.cover(from, to)
                            }
                        }
                    }
                }

                fun query(from: Int, to: Int): Int {
                    val left = from.coerceAtLeast(start)
                    val right = to.coerceAtMost(end)

                    return when {
                        size == 0 || from > end || to < start -> {
                            0
                        }

                        size == end - start + 1 -> {
                            right - left + 1
                        }

                        else -> {
                            children.sumOf {
                                it.query(from, to)
                            }
                        }
                    }
                }
            }

            val root = SegNode(tiles.minOf { it[0] }, tiles.maxOf { it[1] })

            tiles.forEach { (from, to) ->
                root.cover(from, to)
            }

            var result = 0
            tiles.forEach { (from, _) ->
                result = result.coerceAtLeast(root.query(from, from + carpetLen - 1))
            }

            return result
        }
    }

    expect {
        Solution().maximumWhiteTiles(
            arrayOf(
                intArrayOf(1, 5),
                intArrayOf(10, 11),
                intArrayOf(12, 18),
                intArrayOf(20, 25),
                intArrayOf(30, 32),
            ), 10
        )
    }
}