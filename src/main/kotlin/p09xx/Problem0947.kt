package p09xx

import util.expect

fun main() {
class Solution {
    fun removeStones(stones: Array<IntArray>): Int {
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

        val rows = hashMapOf<Int, Group>()
        val columns = hashMapOf<Int, Group>()

        val groups = hashSetOf<Group>()
        stones.forEach { (r, c) ->
            val group = Group()
            rows[r]?.also {
                it.join(group)
            } ?: run {
                rows[r] = group
            }

            columns[c]?.also {
                it.join(group)
            } ?: run {
                columns[c] = group
            }

            groups.add(group)
        }

        return stones.size - groups.map { it.parent }.toSet().size
    }
}

    expect {
        Solution().removeStones(
            arrayOf()
        )
    }
}
