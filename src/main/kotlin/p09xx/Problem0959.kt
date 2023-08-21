package p09xx

import util.expect

fun main() {
    class Solution {
        fun regionsBySlashes(grid: Array<String>): Int {
            class Group {
                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                    }
                    get() {
                        return innerParent?.parent?.also {
                            innerParent = it
                        } ?: this
                    }

                fun join(target: Group) {
                    val leftParent = parent
                    val rightParent = target.parent

                    if (leftParent != rightParent) {
                        leftParent.parent = rightParent
                    }
                }
            }

            val groups = Array(grid.size) {
                Array(grid[it].length) {
                    //top right bottom left
                    arrayOf(Group(), Group(), Group(), Group())
                }
            }
            val TOP = 0
            val RIGHT = 1
            val BOTTOM = 2
            val LEFT = 3
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, c ->
                    val (top, right, bottom, left) = groups[rowIndex][columnIndex]

                    groups.getOrNull(rowIndex - 1)?.getOrNull(columnIndex)?.getOrNull(BOTTOM)?.join(top)
                    groups.getOrNull(rowIndex)?.getOrNull(columnIndex + 1)?.getOrNull(LEFT)?.join(right)
                    groups.getOrNull(rowIndex + 1)?.getOrNull(columnIndex)?.getOrNull(TOP)?.join(bottom)
                    groups.getOrNull(rowIndex)?.getOrNull(columnIndex - 1)?.getOrNull(RIGHT)?.join(left)

                    when (c) {
                        '/' -> {
                            top.join(left)
                            right.join(bottom)
                        }

                        '\\' -> {
                            top.join(right)
                            bottom.join(left)
                        }

                        else -> {
                            top.join(right)
                            right.join(bottom)
                            bottom.join(left)
                        }
                    }
                }
            }

            return groups.map {
                it.map {
                    it.map { it.parent }
                }.flatten()
            }.flatten().toSet().size
        }
    }

    expect {
        Solution().regionsBySlashes(
            arrayOf()
        )
    }
}
