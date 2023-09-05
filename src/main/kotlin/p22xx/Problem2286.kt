package p22xx

import util.expect

fun main() {
    class SegNode(val start: Int, val end: Int, val initSize: Int) {
        var size = initSize.toLong() * (end - start + 1)

        var maxSize = initSize

        val children by lazy {
            val mid = start + (end - start) / 2

            arrayOf(SegNode(start, mid, initSize), SegNode(mid + 1, end, initSize))
        }

        private fun refreshNode() {
            if (start < end) {
                size = children.sumOf { it.size }
                maxSize = children.maxOf { it.maxSize }
            }
        }

        fun gather(from: Int, to: Int, requireSize: Int): IntArray? {
            return when {
                from > end || to < start -> null
                maxSize < requireSize -> null

                start == end -> {
                    size -= requireSize
                    maxSize -= requireSize

                    intArrayOf(start, initSize - size.toInt() - requireSize)
                }

                else -> {
                    (children[0].gather(from, to, requireSize) ?: children[1].gather(from, to, requireSize)).also {
                        refreshNode()
                    }
                }
            }
        }

        fun sumSizes(from: Int, to: Int): Long {
            return when {
                size == 0L || from > end || to < start -> 0L
                from <= start && to >= end -> size
                else -> children.sumOf { it.sumSizes(from, to) }
            }
        }

        fun scatter(from: Int, to: Int, requireSize: Int): Int {
            var booked = 0
            when {
                requireSize == 0 || from > end || to < start || size == 0L -> {
                }

                from <= start && to >= end && requireSize >= size -> {
                    booked = size.toInt()
                    size = 0
                    maxSize = 0
                }

                start == end -> {
                    booked = requireSize
                    size -= requireSize
                    maxSize -= requireSize
                }

                else -> {
                    booked += children[0].scatter(from, to, requireSize)
                    booked += children[1].scatter(from, to, requireSize - booked)

                    refreshNode()
                }
            }

            return booked
        }
    }

    class BookMyShow(n: Int, m: Int) {
        val root = SegNode(0, n - 1, m)

        fun gather(k: Int, maxRow: Int): IntArray {
            return root.gather(0, maxRow, k) ?: intArrayOf()
        }

        fun scatter(k: Int, maxRow: Int): Boolean {
            if (root.sumSizes(0, maxRow) < k) {
                return false
            }
            root.scatter(0, maxRow, k)

            return true
        }
    }

    val book = BookMyShow(19, 9)
    expect { book.scatter(36, 14) }
    expect { book.scatter(12, 12) }
    expect { book.scatter(43, 12) }
    expect { book.scatter(37, 18) }
    expect { book.gather(6, 16).toList() }
    expect { book.gather(4, 17).toList() }
    expect { book.scatter(14, 7) }
}