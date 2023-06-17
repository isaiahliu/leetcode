package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun closedIsland(grid: Array<IntArray>): Int {
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

            val outside = Group()

            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, i ->
                    if (i == 0) {
                        val group = Group()

                        groups[rowIndex - 1 to columnIndex]?.also { group.join(it) }
                        groups[rowIndex to columnIndex - 1]?.also { group.join(it) }

                        if (rowIndex == 0 || columnIndex == 0 || rowIndex == grid.lastIndex || columnIndex == row.lastIndex) {
                            group.join(outside)
                        }

                        groups[rowIndex to columnIndex] = group
                    }
                }
            }

            return groups.values.map { it.parent }.toSet().filter { it != outside.parent }.size
        }
    }

    measureTimeMillis {
        println(
            Solution().closedIsland(
                arrayOf()

            )
        )
    }.also { println("Time cost: ${it}ms") }
}

