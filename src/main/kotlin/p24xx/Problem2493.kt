package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
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

            val adjacent = Array(n) {
                hashSetOf<Int>()
            }

            val groups = Array(n) { Group() }

            edges.forEach { (from, to) ->
                adjacent[from - 1].add(to - 1)
                adjacent[to - 1].add(from - 1)
                groups[from - 1].join(groups[to - 1])
            }

            var result = 0
            groups.indices.groupBy { groups[it].parent }.values.forEach {
                if (it.size <= 2) {
                    result += it.size
                } else {
                    var maxGroups = -1
                    it.forEach loop@{
                        val visited = IntArray(n) { Int.MAX_VALUE }
                        val tasks = LinkedList<Int>()
                        tasks.add(it)
                        visited[it] = 0

                        var group = 0
                        while (tasks.isNotEmpty()) {
                            group++
                            repeat(tasks.size) {
                                adjacent[tasks.poll()].forEach {
                                    when (visited[it]) {
                                        Int.MAX_VALUE -> {
                                            visited[it] = group
                                            tasks.add(it)
                                        }

                                        group, group - 2 -> {
                                        }

                                        else -> return@loop
                                    }
                                }
                            }
                        }

                        maxGroups = maxGroups.coerceAtLeast(group)
                    }

                    if (maxGroups == -1) {
                        return -1
                    } else {
                        result += maxGroups
                    }
                }
            }
            return result
        }
    }

    expect {
        Solution().magnificentSets(
            6, arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 6),
                intArrayOf(2, 3),
                intArrayOf(4, 6)
            )
        )
    }
}