package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun latestDayToCross(row: Int, col: Int, cells: Array<IntArray>): Int {
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

            val area = Array(row) {
                arrayOfNulls<Group>(col)
            }

            var result = cells.size

            val top = Group()
            val bottom = Group()

            while (top.parent != bottom.parent) {
                result--

                val r = cells[result][0] - 1
                val c = cells[result][1] - 1

                Group().also {
                    area[r][c] = it

                    if (r == 0) {
                        it.join(top)
                    }

                    if (r == row - 1) {
                        it.join(bottom)
                    }

                    area.getOrNull(r - 1)?.getOrNull(c)?.join(it)
                    area.getOrNull(r + 1)?.getOrNull(c)?.join(it)
                    area.getOrNull(r)?.getOrNull(c - 1)?.join(it)
                    area.getOrNull(r)?.getOrNull(c + 1)?.join(it)
                }
            }

            return result + 1
        }
    }

    measureTimeMillis {
        Solution().latestDayToCross(
            2, 2, arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 1),
                intArrayOf(2, 2),
            )
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}