package p23xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(n: Int, edges: Array<IntArray>): Long {
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

            val allGroup = Group().also { it.size = n }

            val groups = hashMapOf<Int, Group>()

            edges.forEach { (from, to) ->
                val group1 = groups.computeIfAbsent(from) {
                    allGroup.size--
                    Group().also { it.size++ }
                }
                val group2 = groups.computeIfAbsent(to) {
                    allGroup.size--
                    Group().also { it.size++ }
                }

                group1.join(group2)
            }

            val parents = groups.values.map { it.parent }.distinct()

            var result = allGroup.size.toLong().let { it * (it - 1) / 2 }
            for (i in parents.indices) {
                val size1 = parents[i].size.toLong()
                for (j in i + 1 until parents.size) {
                    result += size1 * parents[j].size
                }

                result += size1 * allGroup.size
            }

            return result
        }
    }

    expect {
        Solution().countPairs(
            1, arrayOf()
        )
    }
}