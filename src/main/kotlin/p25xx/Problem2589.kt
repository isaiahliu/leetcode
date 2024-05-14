package p25xx

import util.expect

fun main() {
    class Solution {
        fun findMinimumTime(tasks: Array<IntArray>): Int {
            class SegNode(val range: IntRange) {
                val left: SegNode by lazy { SegNode(range.first..(range.last + range.first) / 2) }
                val right: SegNode by lazy { SegNode((range.last + range.first) / 2 + 1..range.last) }

                var count = 0

                fun count(l: Int, r: Int): Int {
                    return when {
                        l > range.last || r < range.first -> {
                            0
                        }

                        count == 0 -> {
                            0
                        }

                        count == range.last - range.first + 1 -> {
                            minOf(range.last, r) - maxOf(range.first, l) + 1
                        }

                        else -> left.count(l, r) + right.count(l, r)
                    }
                }

                fun mark(pos: Int): Boolean {
                    return when {
                        pos !in range -> false
                        count == range.last - range.first + 1 -> false

                        range.first == range.last -> {
                            count++
                            true
                        }

                        right.mark(pos) -> {
                            count++
                            true
                        }

                        left.mark(pos) -> {
                            count++
                            true
                        }

                        else -> false
                    }
                }
            }

            val root = SegNode(0..2000)

            tasks.sortBy { it[1] }

            var result = 0
            tasks.forEach { (from, to, duration) ->
                var match = root.count(from, to)
                var current = to
                while (match < duration) {
                    if (root.mark(current--)) {
                        match++
                        result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findMinimumTime(
            arrayOf(
                intArrayOf(1, 9, 5),
                intArrayOf(7, 15, 4),
            )
        )
    }
    expect {
        Solution().findMinimumTime(
            arrayOf(
                intArrayOf(2, 13, 2),
                intArrayOf(6, 18, 5),
                intArrayOf(2, 13, 3),
            )
        )
    }

}