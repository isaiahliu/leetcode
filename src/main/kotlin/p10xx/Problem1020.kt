package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numEnclaves(grid: Array<IntArray>): Int {
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

            val groups = hashMapOf<Pair<Int, Int>, Group>()

            val edge = Group()

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, item ->
                    if (item == 1) {
                        val group = Group()

                        if (rowIndex == 0 || rowIndex == grid.lastIndex || columnIndex == 0 || columnIndex == row.lastIndex) {
                            group.join(edge)
                        }

                        groups[rowIndex - 1 to columnIndex]?.join(group)
                        groups[rowIndex to columnIndex - 1]?.join(group)

                        groups[rowIndex to columnIndex] = group
                    }
                }
            }

            return groups.values.filter { it.parent != edge.parent }.size
        }
    }

    measureTimeMillis {
        Solution().numEnclaves(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}