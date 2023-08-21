package p19xx

import util.expect

fun main() {
    class Solution {
        fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
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

            val groups = Array(n) {
                Group()
            }

            edges.forEach { (from, to) ->
                groups[from].join(groups[to])
            }

            return groups[source].parent == groups[destination].parent
        }
    }

    expect {
        Solution().validPath(
            2, arrayOf(), 1, 1
        )
    }
}