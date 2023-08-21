package p17xx

import util.expect

fun main() {
    class Solution {
        fun minimumHammingDistance(source: IntArray, target: IntArray, allowedSwaps: Array<IntArray>): Int {
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

            val groups = Array(source.size) { Group() }
            allowedSwaps.forEach { (from, to) ->
                groups[from].join(groups[to])
            }

            var result = groups.size
            groups.indices.groupBy { groups[it].parent }.values.forEach {
                val map1 = it.groupingBy { source[it] }.eachCount()
                val map2 = it.groupingBy { target[it] }.eachCount()

                map1.forEach { (key, count) ->
                    map2[key]?.also {
                        result -= count.coerceAtMost(it)
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumHammingDistance(
            intArrayOf(), intArrayOf(), arrayOf()
        )
    }
}
