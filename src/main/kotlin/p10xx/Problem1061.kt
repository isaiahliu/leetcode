package p10xx

import util.expect

fun main() {
    class Solution {
        fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
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

            val groups = Array(26) { Group() }

            s1.forEachIndexed { index, c1 ->
                val n1 = c1 - 'a'
                val n2 = s2[index] - 'a'

                if (n1 != n2) {
                    groups[n1].join(groups[n2])
                }
            }

            val min = hashMapOf<Group, Int>()

            groups.forEachIndexed { index, group ->
                group.parent.also {
                    min[it] = min[it]?.takeIf { it < index } ?: index
                }
            }

            return baseStr.map {
                'a' + (min[groups[it - 'a'].parent] ?: 0)
            }.joinToString("")
        }
    }

    expect {
        Solution().smallestEquivalentString(
            "parker", "morris", "parser"
        )
    }
}