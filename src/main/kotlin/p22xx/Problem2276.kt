package p22xx

import util.expect

fun main() {
    class SegNode(val start: Int, val end: Int) {
        var size = 0

        val children by lazy {
            val mid = start + (end - start) / 2

            arrayOf(SegNode(start, mid), SegNode(mid + 1, end))
        }

        fun add(from: Int, to: Int): Int {
            return when {
                size == end - start + 1 -> {
                    0
                }

                from <= start && to >= end -> {
                    (end - start + 1 - size).also {
                        size = end - start + 1
                    }
                }

                from > end || to < start -> {
                    0
                }

                else -> {
                    children.sumOf {
                        it.add(from, to)
                    }.also {
                        size += it
                    }
                }
            }
        }
    }

    class CountIntervals {
        val root = SegNode(1, 1000000000)

        fun add(left: Int, right: Int) {
            root.add(left, right)
        }

        fun count(): Int {
            return root.size
        }

    }

    val c = CountIntervals()
    expect {
        c.add(2, 3)
        c.count()
    }
}