package p16xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun distanceLimitedPathsExist(n: Int, edgeList: Array<IntArray>, queries: Array<IntArray>): BooleanArray {
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

            val result = BooleanArray(queries.size)
            val groups = Array(n) { Group() }

            val queue = PriorityQueue<IntArray>(compareBy { it[2] })
            edgeList.forEach { queue.add(it) }

            queries.indices.sortedBy { queries[it][2] }.forEach { queryIndex ->
                val (from, to, limit) = queries[queryIndex]

                while (queue.isNotEmpty() && queue.peek()[2] < limit) {
                    queue.poll().also { (f, t, _) ->
                        groups[f].join(groups[t])
                    }
                }

                result[queryIndex] = groups[from].parent == groups[to].parent
            }

            return result
        }
    }

    expect {
        Solution().distanceLimitedPathsExist(
            1, arrayOf(), arrayOf()
        )
    }
}
