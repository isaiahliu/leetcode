package p31xx

import util.expect

fun main() {
    class Solution {
        fun countDays(days: Int, meetings: Array<IntArray>): Int {
            class SegNode(val start: Int, val end: Int) {
                val children by lazy {
                    arrayOf(
                        SegNode(start, (start + end) / 2),
                        SegNode((start + end) / 2 + 1, end)
                    )
                }

                var free = end - start + 1

                fun mark(min: Int, max: Int): Int {
                    return when {
                        min > end || max < start -> 0
                        free == 0 -> 0
                        min <= start && max >= end -> {
                            free.also {
                                free = 0
                            }
                        }

                        else -> {
                            val size = children.sumOf { it.mark(min, max) }

                            free -= size

                            size
                        }
                    }
                }
            }

            val root = SegNode(1, days)

            meetings.forEach { (start, end) ->
                root.mark(start, end)
            }

            return root.free
        }
    }

    expect {
        Solution().countDays(
            1, arrayOf()
        )
    }
}
