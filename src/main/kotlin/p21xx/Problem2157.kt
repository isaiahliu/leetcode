package p21xx

import util.expect
import util.input

fun main() {
    class Solution {
        fun groupStrings(words: Array<String>): IntArray {
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

            fun String.status(): Int {
                var status = 0

                forEach {
                    status = status or (1 shl (it - 'a'))
                }

                return status
            }

            val groups = hashMapOf<Int, Group>()

            words.forEach {
                groups.computeIfAbsent(it.status()) { Group() }.size++
            }

            groups.forEach { (status, group) ->
                for (pos in 0 until 26) {
                    val p = 1 shl pos
                    if (status and p == 0) {
                        groups[status + p]?.join(group)

                        for (change in pos + 1 until 26) {
                            if (status and (1 shl change) > 0) {
                                groups[status + p - (1 shl change)]?.join(group)
                            }
                        }
                    }
                }
            }

            val groupCounts = groups.values.map { it.parent }.toSet()

            return intArrayOf(groupCounts.size, groupCounts.maxOf { it.size })
        }
    }

    expect {
        Solution().groupStrings(
            input.first().split(",").toTypedArray()
        ).toList()
    }
}