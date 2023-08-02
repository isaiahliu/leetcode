package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDays(grid: Array<IntArray>): Int {
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

            fun Pair<Int, Int>.match(): Boolean {
                val groups = hashMapOf<Pair<Int, Int>, Group>()

                grid.forEachIndexed { r, row ->
                    row.forEachIndexed { c, num ->
                        if (num > 0 && (r != this.first || c != this.second)) {
                            val group = Group()
                            groups[r to c] = group
                            groups[r - 1 to c]?.join(group)
                            groups[r to c - 1]?.join(group)
                        }
                    }
                }
                return groups.values.map { it.parent }.toSet().size != 1
            }

            if ((-1 to -1).match()) {
                return 0
            }

            grid.forEachIndexed { r, row ->
                row.forEachIndexed { c, num ->
                    if (num > 0 && (r to c).match()) {
                        return 1
                    }
                }
            }

            return 2
        }
    }

    measureTimeMillis {
        Solution().minDays(
            arrayOf()
        ).also { println(it) }
    }
}

