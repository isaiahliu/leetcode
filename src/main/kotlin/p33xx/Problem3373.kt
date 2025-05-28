package p33xx

import util.expect

fun main() {
    class Solution {
        fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>): IntArray {
            fun Array<IntArray>.groupNode(): Pair<Set<Int>, Set<Int>> {
                val adjacent: Array<MutableSet<Int>> = Array(size + 1) { hashSetOf() }

                forEach { (from, to) ->
                    adjacent[from].add(to)
                    adjacent[to].add(from)
                }

                val indices = arrayOf(hashSetOf<Int>(), hashSetOf())

                fun dfs(node: Int, groupIndex: Int) {
                    if (indices[groupIndex].add(node)) {
                        adjacent[node].forEach {
                            dfs(it, 1 - groupIndex)
                        }
                    }
                }

                dfs(0, 0)

                return indices[0] to indices[1]
            }

            val (odd, even) = edges1.groupNode()

            val max2 = edges2.groupNode().let { (o2, e2) ->
                maxOf(o2.size, e2.size)
            }

            return IntArray(edges1.size + 1) {
                when (it) {
                    in odd -> odd.size
                    in even -> even.size
                    else -> 0
                } + max2
            }
        }
    }

    expect {
        Solution().maxTargetNodes(
            arrayOf(), arrayOf()
        )
    }
}
