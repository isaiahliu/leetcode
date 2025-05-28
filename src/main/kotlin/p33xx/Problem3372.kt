package p33xx

import util.expect

fun main() {
    class Solution {
        fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>, k: Int): IntArray {
            val adjacent1: Array<MutableSet<Int>> = Array(edges1.size + 1) { hashSetOf() }
            val adjacent2: Array<MutableSet<Int>> = Array(edges2.size + 1) { hashSetOf() }

            edges1.forEach { (from, to) ->
                adjacent1[from].add(to)
                adjacent1[to].add(from)
            }
            edges2.forEach { (from, to) ->
                adjacent2[from].add(to)
                adjacent2[to].add(from)
            }

            fun Array<MutableSet<Int>>.count(index: Int, depth: Int): Int {
                if (depth < 0) {
                    return 0
                }

                val visited = hashSetOf<Int>()

                fun dfs(node: Int, d: Int) {
                    if (visited.add(node) && d < depth) {
                        this[node].forEach {
                            dfs(it, d + 1)
                        }
                    }
                }

                dfs(index, 0)

                return visited.size
            }

            val max2 = adjacent2.indices.maxOf {
                adjacent2.count(it, k - 1)
            }

            return adjacent1.indices.map { adjacent1.count(it, k) + max2 }.toIntArray()
        }
    }

    expect {
        Solution().maxTargetNodes(
            arrayOf(), arrayOf(), 0
        )
    }
}
