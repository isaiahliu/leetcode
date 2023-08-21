package p12xx

import util.expect

fun main() {
    class Solution {
        fun smallestStringWithSwaps(s: String, pairs: List<List<Int>>): String {
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

            val chars = s.toCharArray()

            val groups = Array(s.length) { Group() }

            pairs.forEach { (from, to) ->
                groups[from].join(groups[to])
            }

            groups.mapIndexed { index, group -> group.parent to index }
                .groupBy({ it.first }, { it.second }).values.forEach {
                    val sorted = it.sorted()

                    it.map { s[it] }.sorted().forEachIndexed { index, c ->
                        chars[sorted[index]] = c
                    }
                }

            return String(chars)
        }
    }

    expect {
        Solution().smallestStringWithSwaps(
            "", listOf()
        )
    }
}
