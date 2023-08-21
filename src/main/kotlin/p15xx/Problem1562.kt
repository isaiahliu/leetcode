package p15xx

import util.expect

fun main() {
    class Solution {
        fun findLatestStep(arr: IntArray, m: Int): Int {
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

            val groups = hashMapOf<Int, Group>()

            val matchParents = hashSetOf<Group>()

            var result = -1
            arr.forEachIndexed { index, pos ->
                val group = Group().also { it.size++ }

                groups[pos] = group

                groups[pos - 1]?.join(group)
                groups[pos + 1]?.join(group)

                if (matchParents.none { it.parent.size == m }) {
                    matchParents.clear()
                }

                if (group.parent.size == m) {
                    matchParents.add(group.parent)
                }

                if (matchParents.isNotEmpty()) {
                    result = index + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().findLatestStep(
            intArrayOf(3, 5, 1, 2, 4), 1
        )
    }
}

