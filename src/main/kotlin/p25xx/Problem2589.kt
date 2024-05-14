package p25xx

import util.expect
import kotlin.math.max

fun main() {
    class Solution {
        fun findMinimumTime(tasks: Array<IntArray>): Int {
            class SegNode(val range: IntRange) {
                val left: SegNode by lazy { SegNode(range.first..(range.last + range.first) / 2) }
                val right: SegNode by lazy { SegNode((range.last + range.first) / 2 + 1..range.last) }

                var size = 0

                fun count(l: Int, r: Int): Int {
                    return when {
                        l > range.last || r < range.first -> {
                            0
                        }

                        size == 0 -> {
                            0
                        }

                        size == range.last - range.first + 1 -> {
                            minOf(range.last, r) - maxOf(range.first, l) + 1
                        }

                        else -> left.count(l, r) + right.count(l, r)
                    }
                }

                fun mark(rightLimit: Int, markCount: Int): Int {
                    return when {
                        rightLimit < range.first -> 0
                        markCount == 0 -> 0
                        size == range.last - range.first + 1 -> 0

                        rightLimit >= range.last && size == 0 && (range.last - range.first + 1) <= markCount -> {
                            (range.last - range.first + 1).also { size += it }
                        }

                        else -> {
                            val rightCount = right.mark(rightLimit, markCount)
                            val leftCount = left.mark(rightLimit, markCount - rightCount)

                            (leftCount + rightCount).also { size += it }
                        }
                    }
                }
            }

            val root = SegNode(0..2000)

            tasks.sortBy { it[1] }

            var result = 0
            tasks.forEach { (from, to, duration) ->
                max(duration - root.count(from, to), 0).also {
                    result += it
                    root.mark(to, it)
                }
            }

            return result
        }
    }

    expect {
        Solution().findMinimumTime(
            arrayOf(
                intArrayOf(8, 19, 1),
                intArrayOf(6, 13, 3)
            )
        )
    }
}