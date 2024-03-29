package p16xx

import util.expect

fun main() {
    class Solution {
        fun areConnected(n: Int, threshold: Int, queries: Array<IntArray>): List<Boolean> {
            return when {
                threshold == 0 -> {
                    List(queries.size) { true }
                }

                threshold > n / 2 -> {
                    List(queries.size) { false }
                }

                threshold > n / 3 -> {
                    queries.map { (from, to) ->
                        from > threshold && to > threshold && (from % to == 0 || to % from == 0)
                    }
                }

                else -> {
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

                    val groups = Array(n - threshold) { Group() }

                    val visited = hashSetOf<Int>()
                    for (from in threshold + 1..n) {
                        if (!visited.add(from)) {
                            continue
                        }
                        val group = groups[from - threshold - 1]

                        var t = from * 2

                        if (t > n) {
                            break
                        }

                        while (t <= n) {
                            visited.add(t)
                            group.join(groups[t - threshold - 1])
                            t += from
                        }
                    }

                    queries.map { (from, to) ->
                        if (from <= threshold || to <= threshold) {
                            false
                        } else {
                            groups[from - threshold - 1].parent == groups[to - threshold - 1].parent
                        }
                    }
                }
            }
        }
    }

    expect {
        Solution().areConnected(
            5, 3, arrayOf()
        )
    }
}