package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun largestIsland(grid: Array<IntArray>): Int {
            class Group {
                var size = 0

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.size += size
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

            var result: Int? = null

            val size = grid.size
            val groups = Array(size) {
                arrayOfNulls<Group>(size)
            }

            repeat(size) { r ->
                repeat(size) { c ->
                    if (grid[r][c] == 1) {
                        groups[r][c] = Group().also { g ->
                            g.size++
                            groups.getOrNull(r - 1)?.getOrNull(c)?.also { it.join(g) }
                            groups.getOrNull(r)?.getOrNull(c - 1)?.also { it.join(g) }
                        }
                    }
                }
            }

            repeat(size) { r ->
                repeat(size) { c ->
                    if (groups[r][c] == null) {
                        result = (result ?: 0).coerceAtLeast(
                            arrayOf(
                                r - 1 to c,
                                r + 1 to c,
                                r to c - 1,
                                r to c + 1
                            ).mapNotNull {
                                groups.getOrNull(it.first)?.getOrNull(it.second)?.parent
                            }.distinct().map { it.size }.sum() + 1
                        )
                    }
                }
            }

            return result ?: (size * size)
        }
    }

    measureTimeMillis {
        Solution().largestIsland(
            arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}