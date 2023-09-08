package p23xx

import util.expect

fun main() {
    class Solution {
        fun reachableNodes(n: Int, edges: Array<IntArray>, restricted: IntArray): Int {
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

            val groups = Array(n) { Group().also { it.size++ } }
            val restrictedSet = restricted.toSet()

            edges.forEach { (from, to) ->
                if (from !in restrictedSet && to !in restrictedSet) {
                    groups[from].join(groups[to])
                }
            }

            return groups[0].parent.size
        }
    }

    expect {
        Solution().reachableNodes(
            5, arrayOf(), intArrayOf(3, 9, 3)
        )
    }
}