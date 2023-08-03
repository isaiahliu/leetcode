package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxNumEdgesToRemove(n: Int, edges: Array<IntArray>): Int {
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

            val groups = Array(2) { Array(n) { Group().also { it.size++ } } }

            var result = 0
            edges.forEach { (type, from, to) ->
                if (type == 3) {
                    if (groups[0][from - 1].parent == groups[0][to - 1].parent) {
                        result++
                    } else {
                        groups[0][from - 1].join(groups[0][to - 1])
                        groups[1][from - 1].join(groups[1][to - 1])
                    }
                }
            }

            edges.forEach { (type, from, to) ->
                if (type != 3) {
                    groups[type - 1].also {
                        if (it[from - 1].parent == it[to - 1].parent) {
                            result++
                        } else {
                            it[from - 1].join(it[to - 1])
                        }
                    }
                }
            }

            return result.takeIf { groups.all { it[0].parent.size == n } } ?: -1
        }
    }

    measureTimeMillis {
        Solution().maxNumEdgesToRemove(
            4, arrayOf(
                intArrayOf(3, 1, 2),
                intArrayOf(3, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf(1, 2, 4),
                intArrayOf(1, 1, 2),
                intArrayOf(2, 3, 4),
            )
        ).also { println(it) }
    }
}

