package p15xx

import util.expect

fun main() {
    class Solution {
        fun isPrintable(targetGrid: Array<IntArray>): Boolean {
            class Rectangle {
                var top: Int = Int.MAX_VALUE
                var left: Int = Int.MAX_VALUE
                var right: Int = Int.MIN_VALUE
                var bottom: Int = Int.MIN_VALUE

                val cells = hashSetOf<Pair<Int, Int>>()

                fun done(): Boolean {
                    return (bottom - top + 1) * (right - left + 1) == cells.size
                }

                fun add(rowIndex: Int, columnIndex: Int) {
                    top = top.coerceAtMost(rowIndex)
                    bottom = bottom.coerceAtLeast(rowIndex)

                    left = left.coerceAtMost(columnIndex)
                    right = right.coerceAtLeast(columnIndex)

                    cells.add(rowIndex to columnIndex)
                }

                fun giveBack(rowIndex: Int, columnIndex: Int) {
                    if (rowIndex in top..bottom && columnIndex in left..right) {
                        cells.add(rowIndex to columnIndex)
                    }
                }
            }

            val map = hashMapOf<Int, Rectangle>()

            targetGrid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    map.computeIfAbsent(num) {
                        Rectangle()
                    }.add(r, c)
                }
            }

            val rectangles = map.values.toMutableSet()

            while (true) {
                val rectangle = rectangles.firstOrNull { it.done() } ?: break

                rectangles.remove(rectangle)

                rectangles.forEach { rect ->
                    rectangle.cells.forEach { (r, c) ->
                        rect.giveBack(r, c)
                    }
                }
            }

            return rectangles.isEmpty()
        }
    }

    expect {
        Solution().isPrintable(
            arrayOf()
        )
    }
}

