package p24xx

import util.expect

fun main() {
    class Solution {
        fun minScore(n: Int, roads: Array<IntArray>): Int {
            class Group {
                var min = Int.MAX_VALUE

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.min = value.min.coerceAtMost(min)
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

            roads.forEach { (from, to, weight) ->
                groups[from - 1].join(groups[to - 1])

                groups[from - 1].parent.min = groups[from - 1].parent.min.coerceAtMost(weight)
            }

            return groups[0].parent.min
        }
    }

    expect {
        Solution().minScore(
            1, arrayOf()
        )
    }
}