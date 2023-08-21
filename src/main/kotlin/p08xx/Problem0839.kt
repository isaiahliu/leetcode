package p08xx

import util.expect

fun main() {
    class Solution {
        fun numSimilarGroups(strs: Array<String>): Int {
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

            val groups = Array(strs.size) { Group() }

            fun String.similar(target: String): Boolean {
                var index = 0

                val diffs = arrayListOf<Pair<Char, Char>>()

                while (index < length) {
                    val l = this[index]
                    val r = target[index]

                    if (l != r) {
                        when (diffs.size) {
                            0 -> diffs.add(l to r)
                            1 -> {
                                if (l == diffs[0].second && r == diffs[0].first) {
                                    diffs.add(l to r)
                                } else {
                                    return false
                                }
                            }

                            else -> return false
                        }
                    }

                    index++
                }

                return diffs.size != 1
            }

            for (i in 1 until strs.size) {
                for (j in 0 until i) {
                    if (strs[i].similar(strs[j])) {
                        groups[i].join(groups[j])
                    }
                }
            }

            return groups.map { it.parent }.distinct().size
        }
    }

    expect {
        Solution().numSimilarGroups(
            arrayOf(
                "coswcmcgkc",
                "cosgmccwkc",
                "coswmccgkc",
            )
        )
    }
}