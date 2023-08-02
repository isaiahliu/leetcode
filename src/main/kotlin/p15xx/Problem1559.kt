package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun containsCycle(grid: Array<CharArray>): Boolean {
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

            val groups = Array(grid.size) {
                Array(grid[it].size) {
                    Group().also { it.size++ }
                }
            }

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, ch ->
                    val group = groups[r][c]

                    arrayOf(r - 1 to c, r to c - 1).forEach { (nr, nc) ->
                        grid.getOrNull(nr)?.getOrNull(nc)?.takeIf { it == ch }?.also {
                            val neighbor = groups[nr][nc]

                            if (group.parent == neighbor.parent) {
                                if (group.parent.size >= 4) {
                                    return true
                                }
                            } else {
                                group.join(neighbor)
                            }
                        }
                    }
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().containsCycle(
            arrayOf(
                charArrayOf('a', 'a', 'a', 'a'),
                charArrayOf('a', 'b', 'b', 'a'),
                charArrayOf('a', 'b', 'b', 'a'),
                charArrayOf('a', 'a', 'a', 'a'),
            )
        ).also { println(it) }
    }
}

