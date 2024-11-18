package p32xx

import util.expect

fun main() {
    class Solution {
        fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
            class SegNode(val from: Int, val to: Int) {
                private var size = to - from + 1

                private val children by lazy {
                    arrayOf(
                        SegNode(from, (from + to) / 2),
                        SegNode((from + to) / 2 + 1, to)
                    )
                }

                fun mark(l: Int, r: Int): Int {
                    when {
                        size == 0 -> {
                        }

                        l > to || r < from -> {
                        }

                        l <= from && r >= to -> {
                            size = 0
                        }

                        else -> {
                            size = children.sumOf { it.mark(l, r) }
                        }
                    }

                    return size
                }
            }

            val root = SegNode(0, n - 1)

            return queries.map { (from, to) ->
                root.mark(from + 1, to - 1) - 1
            }.toIntArray()
        }
    }

    expect {
        Solution().shortestDistanceAfterQueries(
            4,
            arrayOf(
                intArrayOf(0, 3),
                intArrayOf(0, 2),
            )
        )
    }
//    expect {
//        Solution().shortestDistanceAfterQueries(
//            100000,
//            Array(100000 - 1) {
//                intArrayOf(0, it + 1)
//            }
//        )
//    }
}
