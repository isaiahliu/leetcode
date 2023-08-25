package p20xx

import util.expect

fun main() {
    class Solution {
        fun friendRequests(n: Int, restrictions: Array<IntArray>, requests: Array<IntArray>): BooleanArray {
            class Group(val id: Int) {
                val restrictionIds = hashSetOf<Int>()

                val restrictionGroups = hashSetOf<Int>()

                var innerParent: Group? = null
                    private set

                var parent: Group
                    set(value) {
                        innerParent = value
                        value.restrictionIds.addAll(restrictionIds)
                        value.restrictionGroups.addAll(restrictionGroups)
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

            val groups = Array(n) { Group(it) }

            restrictions.forEachIndexed { index, (from, to) ->
                groups[from].restrictionIds.add(index)
                groups[to].restrictionIds.add(index)
            }

            return requests.map { (from, to) ->
                val group1 = groups[from].parent
                val group2 = groups[to].parent
                when {
                    group1 == group2 -> true
                    group1.id in group2.restrictionGroups -> false
                    else -> {
                        val res = hashSetOf<Int>()
                        res.addAll(group1.restrictionIds)
                        res.addAll(group2.restrictionIds)

                        if (res.size == group1.restrictionIds.size + group2.restrictionIds.size) {
                            group1.join(group2)
                            true
                        } else {
                            group1.restrictionGroups.add(group2.id)
                            group2.restrictionGroups.add(group1.id)

                            false
                        }
                    }
                }
            }.toBooleanArray()
        }
    }

    expect {
        Solution().friendRequests(
            5, arrayOf(), arrayOf()
        )
    }
}