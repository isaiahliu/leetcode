package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun makeConnected(n: Int, connections: Array<IntArray>): Int {
            if (connections.size < n - 1) {
                return -1
            }

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

            val groups = Array(n) { Group() }

            connections.forEach { (from, to) ->
                groups[from].join(groups[to])
            }

            return groups.map { it.parent }.toSet().size - 1
        }
    }

    measureTimeMillis {
        Solution().makeConnected(
            1, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

