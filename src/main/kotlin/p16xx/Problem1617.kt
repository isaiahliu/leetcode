package p16xx

import util.expect

fun main() {
    class Solution {
        fun countSubgraphsForEachDiameter(n: Int, edges: Array<IntArray>): IntArray {
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

            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val neighbors = Array(n) { hashMapOf<Int, Int>() }
            edges.forEachIndexed { index, (from, to) ->
                neighbors[from - 1][to - 1] = index
                neighbors[to - 1][from - 1] = index
            }

            fun dfs(node: Int, status: Int, route: Set<Int>): Int {
                var result = 0

                neighbors[node].forEach { (to, index) ->
                    if (to !in route && (1 shl index) and status > 0) {
                        result = result.coerceAtLeast(1 + dfs(to, status, route + node))
                    }
                }

                return result
            }

            val result = IntArray(n - 1)
            repeat(1 shl edges.size) { status ->
                var max = 0

                val groups = hashMapOf<Int, Group>()
                status.forEachBit {
                    val from = groups.computeIfAbsent(edges[it][0]) { Group().also { it.size++ } }
                    val to = groups.computeIfAbsent(edges[it][1]) { Group().also { it.size++ } }

                    from.join(to)
                }

                if (groups.values.firstOrNull()?.parent?.takeIf { it.size == groups.size } != null) {
                    repeat(n) {
                        max = max.coerceAtLeast(dfs(it, status, emptySet()))
                    }

                    if (max > 0) {
                        result[max - 1]++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countSubgraphsForEachDiameter(
            4, arrayOf(
                intArrayOf(1, 3),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
            )
        ).toList()
    }
}