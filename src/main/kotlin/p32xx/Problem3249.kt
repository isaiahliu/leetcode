package p32xx

import util.expect

fun main() {
    class Solution {
        fun countGoodNodes(edges: Array<IntArray>): Int {
            val adjacent = Array(edges.size + 1) {
                hashSetOf<Int>()
            }

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
            }

            var result = 0
            val visited = hashSetOf(0)

            fun dfs(node: Int): Int {
                val children = adjacent[node].filter { visited.add(it) }.map {
                    dfs(it)
                }

                if (children.distinct().size <= 1) {
                    result++
                }

                return children.sum() + 1
            }

            dfs(0)

            return result
        }
    }

    expect {
        Solution().countGoodNodes(
            arrayOf()
        )
    }
}
